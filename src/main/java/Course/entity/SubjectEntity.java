package Course.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@Table(name = "subject")
public class SubjectEntity extends Auditable{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "isActive")
    private Boolean isActive;

    @ManyToMany(mappedBy = "subjects")
    private List<TeacherEntity> teachers;

}
