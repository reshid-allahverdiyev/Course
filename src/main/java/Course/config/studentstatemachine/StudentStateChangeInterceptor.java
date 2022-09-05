package Course.config.studentstatemachine;

import Course.entity.StudentEntity;
import Course.repository.mysql.StudentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.Message;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.state.State;
import org.springframework.statemachine.support.StateMachineInterceptorAdapter;
import org.springframework.statemachine.transition.Transition;
import org.springframework.stereotype.Component;

import java.util.Optional;

@RequiredArgsConstructor
@Component
public class StudentStateChangeInterceptor extends StateMachineInterceptorAdapter<StudentState, StudentEvent> {

    private final StudentRepository postRepository;

    @Override
    public void preStateChange(State<StudentState, StudentEvent> state, Message<StudentEvent> message, Transition<StudentState, StudentEvent> transition, StateMachine<StudentState, StudentEvent> stateMachine, StateMachine<StudentState, StudentEvent> rootStateMachine) {
        Optional.ofNullable(message).ifPresent(msg -> {
            Optional.ofNullable((Long) msg.getHeaders().getOrDefault("blog_id", -1L)).ifPresent(blogId -> {
                StudentEntity student = postRepository.findById(blogId).get();
                student.setState(state.getId());
                postRepository.save(student);
            });
        });
    }

}