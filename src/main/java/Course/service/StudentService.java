package Course.service;

import Course.config.studentstatemachine.StudentEvent;
import Course.config.studentstatemachine.StudentState;
import Course.config.studentstatemachine.StudentStateChangeInterceptor;
import Course.entity.StudentEntity;
import Course.exception.CustomUserNotFoundException;
import Course.exception.EventNotAcceptableException;
import Course.mapper.ObjectMapper;
import Course.repository.mysql.StudentRepository;
import Course.repository.mysql.view.StudentListView;
import Course.request.CreateStudentRequest;
import Course.request.SearchStudentRequest;
import Course.response.GetOneStudentViewResponse;
import Course.response.GetStudentResponse;
import Course.response.SearchStudentResponse;
import Course.response.searchQuery.SearchQueries;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.messaging.Message;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.statemachine.StateMachine;
import org.springframework.statemachine.config.StateMachineFactory;
import org.springframework.statemachine.support.DefaultStateMachineContext;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StateMachineFactory<StudentState, StudentEvent> studentStateMachineFactory;

    @Autowired
    private StudentStateChangeInterceptor studentStateChangeInterceptor;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public SearchStudentResponse getAllStudent(SearchStudentRequest request, int page, int size) {
        SearchStudentResponse response = new SearchStudentResponse();

        Page<StudentEntity> studentEntityPage = studentRepository
                .findAll(
                        SearchQueries.createStudentSpecification(request) ,
                        PageRequest.of(page, size)
                );

        response.setStudentList(
                studentEntityPage.getContent().stream()
                        .map(objectMapper::entityToDto)
                        .collect(Collectors.toList())
        );
        response.setTotalPages(studentEntityPage.getTotalPages());
        response.setTotalElements(studentEntityPage.getTotalElements());

        return response;
    }

    public GetOneStudentViewResponse getStudentById(Long id) {
     return objectMapper.entityToDto(studentRepository.findById(id, StudentListView.class));
    }

    public GetStudentResponse createStudent(CreateStudentRequest request){
        StudentEntity entity = objectMapper.dtoToEntity(request);
        entity.setState(StudentState.WAITING_FOR_REVIEW);
        return objectMapper.entityToDto(studentRepository.save(entity));
    }

    public GetStudentResponse getForReview(Long studentId){
        StateMachine<StudentState,StudentEvent> sm = build(studentId);
        sendEvent(studentId,sm,StudentEvent.GET_FOR_REVIEW);
        return objectMapper.entityToDto(studentRepository.findById(studentId).get());
    }


    public GetStudentResponse updateStudent(CreateStudentRequest request, Long id) {
        StudentEntity entity =
                studentRepository.findById(id).orElseThrow(NullPointerException::new);

        StudentEntity updatedEntity = studentRepository.save(objectMapper.updateEntity(request, entity));

        return objectMapper.entityToDto(updatedEntity);
    }


    private void sendEvent(Long blogId, StateMachine<StudentState, StudentEvent> sm, StudentEvent event) {
        Message<StudentEvent> msg = MessageBuilder.withPayload(event)
                .setHeader("blog_id", blogId)
                .setHeader("adf","asdf")
                .build();
        if (!sm.sendEvent(msg)) {
            throw new EventNotAcceptableException();
        }
    }

    private StateMachine<StudentState, StudentEvent> build(Long blogId) {
        StudentEntity student = studentRepository.findById(blogId).get();

        StateMachine<StudentState, StudentEvent> sm = studentStateMachineFactory.getStateMachine(Long.toString(blogId));

        sm.stop();

        sm.getStateMachineAccessor()
                .doWithAllRegions(sma -> {
                    sma.addStateMachineInterceptor(studentStateChangeInterceptor);
                    sma.resetStateMachine(new DefaultStateMachineContext<>(student.getState(), null, null, null));
                });

        sm.start();

        return sm;
    }
}
