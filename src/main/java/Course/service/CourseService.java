package Course.service;

import Course.entity.CourseEntity;
import Course.mapper.ObjectMapper;
import Course.repository.mysql.CourseRepository;
import Course.repository.mysql.TeacherCourseRepository;
import Course.repository.mysql.TeacherRepository;
import Course.request.CreateCourseRequest;
import Course.response.GetCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private TeacherCourseRepository teacherCourseRepository;

    @Autowired
    private TeacherRepository teacherRepository;


    @Autowired
    private ObjectMapper objectMapper;

    public List<GetCourseResponse> getAllCourse() {
        return courseRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public GetCourseResponse getCourseById(Long id) {
        return objectMapper.entityToDto(courseRepository.getById(id));
    }

    public GetCourseResponse createCourse(CreateCourseRequest request) {
        CourseEntity entity = objectMapper.dtoToEntity(request);
        return objectMapper.entityToDto(courseRepository.save(entity));
    }


    public GetCourseResponse updateCourse(CreateCourseRequest request, Long id) {
        CourseEntity entity =
                courseRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());

        return objectMapper.entityToDto(courseRepository.save(entity));
    }
}
