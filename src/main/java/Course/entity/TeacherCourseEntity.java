package Course.entity;


import lombok.Data;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Data
@Table(name = "teacher_course")
public class TeacherCourseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private TeacherEntity teacher;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity course;

    @Column(name = "date_time")
    private LocalDateTime dateTime;

    @PrePersist
    public void create() {
        this.dateTime=LocalDateTime.now();
    }

}
