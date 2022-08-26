package Course.response;

import Course.request.CreateCourseRequest;
import Course.request.CreateTeacherRequest;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class GetTeacherCourseResponse {

    private  Long id;
    private CreateTeacherRequest teacher;
    private CreateCourseRequest course;
    private LocalDateTime dateTime;
}
