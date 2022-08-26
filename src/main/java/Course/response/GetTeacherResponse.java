package Course.response;

import lombok.Data;

import java.util.List;

@Data
public class GetTeacherResponse {
    private String name;
    private String surname;
    private List<GetStudentResponse> students;
    private List<GetSubjectResponse> subjects;
}
