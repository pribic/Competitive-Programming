package example;
import com.amazon.ask.Skill;
import com.amazon.ask.SkillStreamHandler;
import com.amazon.ask.Skills;
import com.amazonaws.services.lambda.runtime.Context; 
import com.amazonaws.services.lambda.runtime.LambdaLogger;
import example.handlers.HelloWorldIntentHandler;

public class Hello extends SkillStreamHandler {
    private static Skill getSkill() {
        return Skills.standard()
          .addRequestHandlers(
            new HelloWorldIntentHandler()
          )
          .build();
    }

    public Hello() {
        super(getSkill());
    }
}