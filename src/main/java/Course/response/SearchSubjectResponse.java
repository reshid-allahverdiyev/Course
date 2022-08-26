package Course.response;

import lombok.Data;

import java.util.List;

@Data
public class SearchSubjectResponse {
    private Integer totalPages;
    private Long totalElements;
    private List<GetSubjectResponse> subjectList;
}
