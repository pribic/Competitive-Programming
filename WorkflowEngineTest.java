/*
 * ******************************************************************************
 *  *                         NOTICE
 *  *
 *  * THIS SOFTWARE IS THE PROPERTY OF AND CONTAINS
 *  * CONFIDENTIAL INFORMATION OF INFOR AND/OR ITS AFFILIATES
 *  * OR SUBSIDIARIES AND SHALL NOT BE DISCLOSED WITHOUT PRIOR
 *  * WRITTEN PERMISSION. LICENSED CUSTOMERS MAY COPY AND
 *  * ADAPT THIS SOFTWARE FOR THEIR OWN USE IN ACCORDANCE WITH
 *  * THE TERMS OF THEIR SOFTWARE LICENSE AGREEMENT.
 *  * ALL OTHER RIGHTS RESERVED.
 *  *
 *  * (c) COPYRIGHT 2020 INFOR.  ALL RIGHTS RESERVED.
 *  * THE WORD AND DESIGN MARKS SET FORTH HEREIN ARE
 *  * TRADEMARKS AND/OR REGISTERED TRADEMARKS OF INFOR
 *  * AND/OR ITS AFFILIATES AND SUBSIDIARIES. ALL RIGHTS
 *  * RESERVED.  ALL OTHER TRADEMARKS LISTED HEREIN ARE
 *  * THE PROPERTY OF THEIR RESPECTIVE OWNERS.
 *  ******************************************************************************
 */
package com.birst.scheduler.workflow;

import com.birst.acornService.client.model.SetVariableInSpaceResponse;
import com.birst.database.ConnectionPool;
import com.birst.email.EmailService;
import com.birst.scheduler.SchedulerContextListener;
import com.birst.scheduler.loginParams.AcornRestClient;
import com.birst.scheduler.utils.AcornAdminDBConnection;
import com.birst.scheduler.utils.Util;
import com.birst.scheduler.workflow.dal.WorkflowDal;
import com.birst.scheduler.workflow.dal.mongo.MongoWorkflowDal;
import com.birst.scheduler.workflow.exception.WorkflowException;
import com.birst.scheduler.workflow.model.Step;
import com.birst.scheduler.workflow.model.Task;
import com.birst.scheduler.workflow.model.TaskType;
import com.birst.scheduler.workflow.model.Workflow;
import com.birst.scheduler.workflow.task.TaskInputParamConstants;
import com.birst.scheduler.workflow.task.executor.SetVariableTaskExecutor;
import com.birst.scheduler.workflow.task.executor.TaskExecutorFactory;
import com.birst.scheduler.workflow.worker.TaskWorkerFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import com.google.inject.util.Modules;
import org.junit.Test;
import org.mockito.Mockito;
import org.testng.Assert;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Matchers.anyObject;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class WorkflowEngineTest {

  private static final int CONCURRENT_TASK = 1;

  @Test
  public void testExecute() throws SQLException, WorkflowException, InterruptedException {
    //build a sample workflow with 1 step and 1 task
    Workflow workflow = new Workflow();
    Step step1 = new Step();
    Task task1 = new Task();
    task1.setInputType(Task.TaskInputType.Default);
    task1.setType(TaskType.SetVariable.name());
    Map<String, Object> taskInput = new HashMap<>();
    taskInput.put(TaskInputParamConstants.SPACE_ID, "space1");
    taskInput.put(TaskInputParamConstants.VAR_NAME, "var1");
    taskInput.put(TaskInputParamConstants.QUERY, "val1");
    task1.setParams(taskInput);
    step1.getTasks().add(task1);
    workflow.getSteps().add(step1);
    // end of sample workflow creation
    SetVariableInSpaceResponse setVariableInSpaceResponse = new SetVariableInSpaceResponse();
    setVariableInSpaceResponse.setSuccess(true);
    SchedulerContextListener.INJECTOR = Guice.createInjector(new TestModule(workflow, setVariableInSpaceResponse));
    WorkflowEngine workflowEngine = SchedulerContextListener.INJECTOR.getInstance(WorkflowEngine.class);
    workflowEngine.execute();

    SetVariableInSpaceResponse setVariableInSpaceResponse1 = new SetVariableInSpaceResponse();
    setVariableInSpaceResponse.setSuccess(false);
    setVariableInSpaceResponse1.setErrorMessage("SetVariable failed");
    SchedulerContextListener.INJECTOR = Guice.createInjector(new TestModule(workflow, setVariableInSpaceResponse1));
    workflowEngine = SchedulerContextListener.INJECTOR.getInstance(WorkflowEngine.class);
    boolean throwException = false;
    try {
      workflowEngine.execute();
    } catch (WorkflowException e) {
      Assert.assertEquals("\nStep: Step-1\n" +
        "Task Type: SetVariable\n" +
        "Reason: SetVariable failed", e.getMessage());
      throwException = true;
    }
    Assert.assertTrue(throwException);

    SchedulerContextListener.INJECTOR = Guice.createInjector(Modules.override(new TestModule(workflow, setVariableInSpaceResponse)).with(new AbstractModule() {
      @Override
      protected void configure() {

      }
    }));
  }



  private static class TestModule extends AbstractModule {
    private final Workflow workflow;
    private final SetVariableInSpaceResponse setVariableInSpaceResponse;

    public TestModule(Workflow workflow, SetVariableInSpaceResponse setVariableInSpaceResponse) {
      this.workflow = workflow;
      this.setVariableInSpaceResponse = setVariableInSpaceResponse;
    }

    @Override
    protected void configure() {
      bind(Workflow.class).toInstance(workflow);
      WorkflowDal workflowDal = mock(MongoWorkflowDal.class);
      bind(WorkflowDal.class).toInstance(workflowDal);

      bind(ExecutorService.class).annotatedWith(Names.named("WorkflowExecutorPool"))
        .toInstance(Executors.newFixedThreadPool(CONCURRENT_TASK));

      EmailService emailService = mock(EmailService.class);
      bind(EmailService.class).toInstance(emailService);

      AcornAdminDBConnection acornAdminDBConnection = mock(AcornAdminDBConnection.class);
      ConnectionPool connectionPool = mock(ConnectionPool.class);
      Connection conn = mock(Connection.class);

      try {
        //set up mocks for admin db connection
        bind(AcornAdminDBConnection.class).toInstance(acornAdminDBConnection);
        when(acornAdminDBConnection.getConnectionPool()).thenReturn(connectionPool);
        when(connectionPool.getConnection()).thenReturn(conn);
      } catch (SQLException throwable) {
        throwable.printStackTrace();
      }

      try {
        Util util = mock(Util.class);
        when(util.getUserEmail(anyString(), anyObject())).thenReturn("workflow@birst.com");
        bind(Util.class).toInstance(util);
      } catch (SQLException e) {
        e.printStackTrace();
      }

      //TaskWorkerFactory taskWorkerFactory = mock(TaskWorkerFactory.class);
      //when(taskWorkerFactory.create(anyObject(), anyObject()))
      //bind(TaskWorkerFactory.class).toInstance(taskWorkerFactory);
      install(new FactoryModuleBuilder().build(TaskWorkerFactory.class));

      TaskExecutorFactory taskExecutorFactory = mock(TaskExecutorFactory.class);
      AcornRestClient acornRestClient = Mockito.mock(AcornRestClient.class);
      when(acornRestClient.setVariableInSpace(anyString(), anyObject())).thenReturn(setVariableInSpaceResponse);
      SetVariableTaskExecutor setVariableTaskExecutor = new SetVariableTaskExecutor(workflow.getSteps().get(0).getTasks().get(0), null, acornRestClient);
      when(taskExecutorFactory.getInstance(anyObject(), anyObject(), anyObject())).thenReturn(setVariableTaskExecutor);
      bind(TaskExecutorFactory.class).toInstance(taskExecutorFactory);
    }
  }

}
