package Course.response;

import Course.constraints.Status;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {
    private String status;
    private LocalDateTime localDateTime;
}
