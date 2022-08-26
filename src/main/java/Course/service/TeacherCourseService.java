package Course.service;

import Course.entity.CourseEntity;
import Course.entity.TeacherCourseEntity;
import Course.entity.TeacherEntity;
import Course.mapper.ObjectMapper;
import Course.repository.mysql.CourseRepository;
import Course.repository.mysql.TeacherCourseRepository;
import Course.repository.mysql.TeacherRepository;
import Course.response.GetTeacherCourseResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherCourseService {

    @Autowired
    TeacherCourseRepository teacherCourseRepository;
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    CourseRepository courseRepository;

    @Autowired
    ObjectMapper objectMapper;


    public List<GetTeacherCourseResponse> getAllTeacherCourse() {
        return teacherCourseRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public GetTeacherCourseResponse getTeacherCourseById(Long id) {
        return objectMapper.entityToDto(teacherCourseRepository.getById(id));
    }

    public GetTeacherCourseResponse joinTeacherCourse(Long courseId, Long teacherId) {
        TeacherCourseEntity entity = new TeacherCourseEntity();

        TeacherEntity teacherEntity = teacherRepository.getById(teacherId);
        CourseEntity courseEntity = courseRepository.getById(courseId);

        entity.setTeacher(teacherEntity);
        entity.setCourse(courseEntity);
        return objectMapper.entityToDto( teacherCourseRepository.save(entity));
    }

    public GetTeacherCourseResponse updateTeacherCourse(Long courseId, Long teacherId, Long id) {
        TeacherCourseEntity entity =
                teacherCourseRepository.findById(id).orElseThrow(NullPointerException::new);
        TeacherEntity teacherEntity = teacherRepository.getById(teacherId);
        CourseEntity courseEntity = courseRepository.getById(courseId);

        entity.setTeacher(teacherEntity);
        entity.setCourse(courseEntity);
        return objectMapper.entityToDto( teacherCourseRepository.save(entity));
    }
}
