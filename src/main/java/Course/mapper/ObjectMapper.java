package Course.mapper;

import Course.entity.*;
import Course.repository.mysql.view.StudentListView;
import Course.request.CreateCourseRequest;
import Course.request.CreateStudentRequest;
import Course.request.CreateSubjectRequest;
import Course.request.CreateTeacherCourseRequest;
import Course.response.*;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public abstract class ObjectMapper {

    public abstract GetStudentResponse entityToDto(StudentEntity entity);

    public abstract GetTeacherResponse entityToDto(TeacherEntity entity);

    public abstract GetSubjectResponse entityToDto(SubjectEntity entity);

    public abstract SubjectEntity dtoToEntity(CreateSubjectRequest request);

    public abstract StudentEntity dtoToEntity(CreateStudentRequest request);


    public abstract CourseEntity dtoToEntity(CreateCourseRequest request);

    public abstract GetCourseResponse entityToDto(CourseEntity entity);

    public abstract GetOneStudentViewResponse entityToDto(StudentListView entity);
    public abstract GetOneStudentViewResponse entityToDto2(StudentListView entity);

    public abstract TeacherCourseEntity dtoToEntity(CreateTeacherCourseRequest request);

    public abstract GetTeacherCourseResponse entityToDto(TeacherCourseEntity entity);

    public StudentEntity updateEntity(CreateStudentRequest request, StudentEntity entity) {
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        StudentInfoEntity studentInfoEntity = new StudentInfoEntity();
        studentInfoEntity.setId(request.getStudentInfo().getId());
        studentInfoEntity.setAddress(request.getStudentInfo().getAddress());
        studentInfoEntity.setEmail(request.getStudentInfo().getEmail());
        entity.setStudentInfo(studentInfoEntity);
        return entity;
    }


    public abstract TokenEntity authResponseToCache(AuthResponse authResponse);
    public abstract TokenEntity2 authResponseToCache2(AuthResponse authResponse);
}
