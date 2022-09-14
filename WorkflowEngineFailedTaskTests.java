/*
 * Copyright (C) 2007-2018 Birst, Inc. All rights reserved.
 * BIRST PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 */
package com.birst.scheduler.workflow;

import com.birst.email.EmailService;
import com.birst.scheduler.SchedulerContextListener;
import com.birst.scheduler.workflow.exception.WorkflowException;
import com.birst.scheduler.workflow.model.Step;
import com.birst.scheduler.workflow.model.Task;
import com.birst.scheduler.workflow.model.TaskStatus;
import com.birst.scheduler.workflow.model.TaskType;
import com.birst.scheduler.workflow.task.executor.TaskExecutor;
import com.birst.scheduler.workflow.worker.TaskWorkerFactory;
import com.birst.test.DataProviderBuilder;
import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Names;
import org.mockito.Mockito;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;

/**
 * @author Dhwaneet Bhatt
 * @since Apr 23, 2018
 */
public class WorkflowEngineFailedTaskTests {

  private final EmailService emailServiceMock = mock(EmailService.class);

  private class TestModule extends AbstractModule {
    @Override
    protected void configure() {
      bind(EmailService.class).toInstance(emailServiceMock);
      install(new FactoryModuleBuilder().build(TaskWorkerFactory.class));
      bind(ExecutorService.class).annotatedWith(Names.named("WorkflowExecutorPool"))
        .toInstance(Executors.newFixedThreadPool(1));
    }
  }

  @BeforeMethod
  private void initGuiceModule() {
    Mockito.reset(emailServiceMock);

    SchedulerContextListener.INJECTOR = Guice.createInjector(new TestModule());
  }

  @DataProvider(name = "HandleFailTask")
  public Iterator<Object[]> testHandleFailTaskDataProvider() {
    int index = 5;
    String taskErrorMessage = "Something went wrong";
    TaskStatus taskStatus = TaskStatus.State.FAILED.createInstance();
    taskStatus.setErrorMessage(taskErrorMessage);

    // ready the request
    Step step1 = new Step();
    Task task1 = new Task();
    task1.setType(TaskType.Processing.name());
    String errorMessage1 =
      String.format("\nStep: Step-%d\nTask Type: %s\nReason: %s", index + 1, task1.getType(), taskErrorMessage);

    Step step2 = new Step();
    step2.setName("AwesomeStep");
    Task task2 = new Task();
    task2.setName("AwesomeTask");
    task2.setType(TaskType.Processing.name());
    String errorMessage2 =
      String.format("\nStep: %s\nTask: %s\nTask Type: %s\nReason: %s", step2.getName(), task2.getName(),
        task1.getType(), taskErrorMessage);

    return DataProviderBuilder.builder()
      // TEST 1 - without step name and task name
      .addParams(index, step1, task1, taskStatus, errorMessage1)
      // TEST 2 - with step name and task name
      .addParams(index, step2, task2, taskStatus, errorMessage2)
      .build();
  }

  @Test(dataProvider = "HandleFailTask")
  public void testHandleFailTask(int stepIndex, Step step, Task task, TaskStatus taskStatus,
                                 String expectedErrorMessage) throws Exception {
    // create the mocks
    TaskExecutor executorMock = mock(TaskExecutor.class);

    // stub the methods
    when(executorMock.getTask()).thenReturn(task);
    when(executorMock.getStatus()).thenReturn(taskStatus);

    // make the request
    WorkflowEngine workflowEngine = SchedulerContextListener.INJECTOR.getInstance(WorkflowEngine.class);
    try {
      workflowEngine.handleFailTask(stepIndex, step, executorMock);
    } catch (WorkflowException e) {
      // verify
      assertEquals(e.getMessage(), expectedErrorMessage);
    }
  }
}
