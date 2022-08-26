package Course.repository.mysql;

import Course.entity.SubjectEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SubjectRepository extends PagingAndSortingRepository<SubjectEntity, Long> {
    List<SubjectEntity> findAllByIdIn(List<Long> ids);
}
