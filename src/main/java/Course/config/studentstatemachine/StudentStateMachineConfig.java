package Course.config.studentstatemachine;

import org.springframework.context.annotation.Configuration;
import org.springframework.statemachine.config.EnableStateMachineFactory;
import org.springframework.statemachine.config.EnumStateMachineConfigurerAdapter;
import org.springframework.statemachine.config.builders.StateMachineStateConfigurer;
import org.springframework.statemachine.config.builders.StateMachineTransitionConfigurer;

import java.util.EnumSet;

@Configuration
@EnableStateMachineFactory
public class StudentStateMachineConfig extends EnumStateMachineConfigurerAdapter<StudentState, StudentEvent> {

    @Override
    public void configure(StateMachineStateConfigurer<StudentState, StudentEvent> states) throws Exception {
        states.withStates().initial(StudentState.WAITING_FOR_REVIEW)
                .states(EnumSet.allOf(StudentState.class));
    }

    @Override
    public void configure(StateMachineTransitionConfigurer<StudentState, StudentEvent> transitions) throws Exception {
        transitions
                .withExternal()
                .source(StudentState.WAITING_FOR_REVIEW)
                .target(StudentState.IN_REVIEW)
                .event(StudentEvent.GET_FOR_REVIEW)
                .and()
                .withExternal()
                .source(StudentState.IN_REVIEW)
                .target(StudentState.APPROVED)
                .event(StudentEvent.APPROVE)
                .and()
                .withExternal()
                .source(StudentState.IN_REVIEW)
                .target(StudentState.REJECTED)
                .event(StudentEvent.REJECT);
    }
}
