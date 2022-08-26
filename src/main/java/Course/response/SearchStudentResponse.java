package Course.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchStudentResponse {

    private Integer totalPages;
    private Long totalElements;
    private List<GetStudentResponse> studentList;

}
