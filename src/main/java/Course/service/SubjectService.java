package Course.service;

import Course.entity.SubjectEntity;
import Course.mapper.ObjectMapper;
import Course.repository.mysql.SubjectRepository;
import Course.request.CreateSubjectRequest;
import Course.response.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
public class SubjectService {

    @Autowired
    private SubjectRepository subjectRepository;

    @Autowired
    private ObjectMapper objectMapper;

//    public List<GetSubjectResponse> getAllSubject() {
//        return subjectRepository.findAll().stream()
//                .map(objectMapper::entityToDto)
//                .collect(Collectors.toList());
//    }


    public SearchSubjectResponse getAllSubject(int page, int size) {
        SearchSubjectResponse response = new SearchSubjectResponse();

        Page<SubjectEntity> subjectEntityPage = subjectRepository
                .findAll(PageRequest.of(page, size, Sort.by("id").descending()));

        response.setSubjectList(
                subjectEntityPage.getContent().stream()
                        .map(objectMapper::entityToDto)
                        .collect(Collectors.toList())
        );
        response.setTotalPages(subjectEntityPage.getTotalPages());
        response.setTotalElements(subjectEntityPage.getTotalElements());

        return response;
    }

//    public GetSubjectResponse getSubjectById(Long id) {
//        return objectMapper.entityToDto(subjectRepository.getById(id));
//    }

    public GetSubjectResponse createSubject(CreateSubjectRequest request) {
        SubjectEntity entity = objectMapper.dtoToEntity(request);
        return objectMapper.entityToDto(subjectRepository.save(entity));
    }


    public GetSubjectResponse updateSubject(CreateSubjectRequest request, Long id) {
        SubjectEntity entity =
                subjectRepository.findById(id).orElseThrow(NullPointerException::new);

        entity.setName(request.getName());

        return objectMapper.entityToDto(subjectRepository.save(entity));
    }
}
