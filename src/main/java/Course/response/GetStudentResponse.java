package Course.response;

import lombok.Data;

@Data
public class GetStudentResponse {

    private Long id;
    private String name;
    private String surname;
    //    private GetStudentInfoResponse studentInfo;
    private GetSearchedTeacherResponse teacher;

}
