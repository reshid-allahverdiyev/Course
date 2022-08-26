package Course.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "student_info")
public class StudentInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "address")
    private String address;

    @OneToOne(mappedBy = "studentInfo")
    private StudentEntity student;

}
