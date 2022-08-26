package Course.service;

import Course.entity.StudentEntity;
import Course.exception.CustomUserNotFoundException;
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
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class StudentService {

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
        return objectMapper.entityToDto(studentRepository.save(entity));
    }

    public GetStudentResponse updateStudent(CreateStudentRequest request, Long id) {
        StudentEntity entity =
                studentRepository.findById(id).orElseThrow(NullPointerException::new);

        StudentEntity updatedEntity = studentRepository.save(objectMapper.updateEntity(request, entity));

        return objectMapper.entityToDto(updatedEntity);
    }

}
