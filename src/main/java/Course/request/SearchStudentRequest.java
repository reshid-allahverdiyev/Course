package Course.request;

import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Component
public class SearchStudentRequest {
    private String name;
    private Long teacherId;
}
