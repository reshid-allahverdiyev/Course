package Course.service;

import Course.entity.SubjectEntity;
import Course.entity.TeacherEntity;
import Course.mapper.ObjectMapper;
import Course.repository.mysql.SubjectRepository;
import Course.repository.mysql.TeacherRepository;
import Course.request.CreateTeacherRequest;
import Course.response.GetTeacherResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TeacherService {
    @Autowired
    TeacherRepository teacherRepository;

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    ObjectMapper objectMapper;

    public List<GetTeacherResponse> getAllTeacher(){
        return teacherRepository.findAll().stream()
                .map(objectMapper::entityToDto)
                .collect(Collectors.toList());
    }

    public GetTeacherResponse getTeacherById(Long id){
        return objectMapper.entityToDto(teacherRepository.getById(id));
    }

    public GetTeacherResponse createTeacher(CreateTeacherRequest request){
        TeacherEntity st = new TeacherEntity();
        st.setName(request.getName());
        st.setSurname(request.getSurname());
        List<SubjectEntity> subjects = subjectRepository.findAllByIdIn(request.getSubjectIds());
        st.setSubjects(subjects);
        return objectMapper.entityToDto(teacherRepository.save(st));
    }

    public GetTeacherResponse updateTeacher(CreateTeacherRequest request,Long id){
        TeacherEntity entity  =
                teacherRepository.findById(id).orElseThrow(NullPointerException::new);
        entity.setId(id);
        entity.setName(request.getName());
        entity.setSurname(request.getSurname());
        List<SubjectEntity> subjects = subjectRepository.findAllByIdIn(request.getSubjectIds());
        entity.setSubjects(subjects);
        return objectMapper.entityToDto(teacherRepository.save(entity));
    }

}
