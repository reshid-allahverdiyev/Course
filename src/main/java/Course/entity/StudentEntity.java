package Course.entity;

import Course.config.studentstatemachine.StudentState;
import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student")
public class StudentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @ManyToOne
    @JoinColumn(name="teacher_id",insertable = false)
    private TeacherEntity teacher;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "info_id")
    private StudentInfoEntity studentInfo;

    @Column(name = "state")
    @Enumerated(EnumType.STRING)
    private StudentState state;

}
