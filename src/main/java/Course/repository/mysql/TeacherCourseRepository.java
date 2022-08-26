package Course.repository.mysql;

import Course.entity.TeacherCourseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeacherCourseRepository extends JpaRepository<TeacherCourseEntity,Long> {
}
