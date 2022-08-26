package Course.response;

import lombok.Data;

@Data
public class GetStudentInfoResponse {
    private Long id;
    private String email;
    private String address;
}
