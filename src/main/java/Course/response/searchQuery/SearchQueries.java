package Course.response.searchQuery;

import Course.entity.StudentEntity;
import Course.request.SearchStudentRequest;
import Course.specification.StudentSpecification;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

@Component
public interface SearchQueries {

    static Specification<StudentEntity> createStudentSpecification(SearchStudentRequest request){
        return new StudentSpecification(request);
    }


}
