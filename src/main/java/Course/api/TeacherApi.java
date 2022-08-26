package Course.api;

import Course.JDBC1.EmailDetails;
import Course.JDBC1.JdbcTest;
import Course.JDBC1.SendMail;
import Course.JDBC1.teacher;
import Course.entity.TeacherEntity;
import Course.request.CreateTeacherRequest;
import Course.response.GetStudentResponse;
import Course.response.GetTeacherResponse;
import Course.service.StudentService;
import Course.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherApi {

    @Autowired
    private TeacherService teacherService;

    @Autowired
    private JdbcTest jdbcTest;

    @Autowired
    private SendMail sendMail;

    @GetMapping("/all")
    public List<GetTeacherResponse> getAllTeacher() {
        return teacherService.getAllTeacher();
    }

    @GetMapping("/all1")
    public String getAllTeacher1() throws SQLException {
        EmailDetails details=new EmailDetails();
        details.setAttachment("dsc");
        details.setRecipient("frpblcccnt@gmail.com");
        details.setSubject("Test");
        details.setMsgBody("abc");
//        return sendMail.sendSimpleMail(details);
        return null;
    }

    @GetMapping("/{id}")
    public GetTeacherResponse getTeacherById(
            @PathVariable("id") Long id
    ) {
        return teacherService.getTeacherById(id);
    }

    @PostMapping("/create")
    public GetTeacherResponse createTeacher(
            @RequestBody CreateTeacherRequest request
    ) {
        return teacherService.createTeacher(request);
    }

    @PutMapping("/update/{id}")
    public GetTeacherResponse createTeacher(
            @RequestBody CreateTeacherRequest request,
            @PathVariable Long id
    ) {
        return teacherService.updateTeacher(request, id);
    }


}