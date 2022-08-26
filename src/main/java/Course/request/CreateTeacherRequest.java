package Course.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateTeacherRequest {
    private String name;
    private String surname;
    private List<Long> subjectIds;
}
