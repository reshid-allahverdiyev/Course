package Course.specification;

import Course.entity.StudentEntity;
import Course.entity.StudentEntity_;
import Course.request.SearchStudentRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Component
public class StudentSpecification implements Specification<StudentEntity> {

    private final SearchStudentRequest request;

    public StudentSpecification(SearchStudentRequest request) {
        this.request = request;
    }

    @Override
    public Predicate toPredicate(Root<StudentEntity> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
        List<Predicate> predicates = new ArrayList<>();

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getName())){
                predicates.add(cb.like(root.get(StudentEntity_.name),"%"+request.getName()+"%"));
            }
        }

        if (Objects.nonNull(request)) {
            if (Objects.nonNull(request.getTeacherId())){
                predicates.add(
                        cb.equal(root.join("teacher").get("id"),request.getTeacherId())
                );
            }
        }

        return cb.and(predicates.toArray(new Predicate[]{}));
    }
}
