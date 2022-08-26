package Course.repository.mysql;

import Course.entity.TeacherEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface TeacherRepository extends JpaRepository<TeacherEntity,Long> {
}
