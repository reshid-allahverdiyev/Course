package Course.api;

import Course.response.GetCourseResponse;
import Course.response.GetTeacherCourseResponse;
import Course.service.CourseService;
import Course.service.TeacherCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class TeacherCourseApi {

    @Autowired
    private TeacherCourseService teacherCourseService;


    @GetMapping("/teacher/all")
    public List<GetTeacherCourseResponse> getAllTeacherCourse() {

        return teacherCourseService.getAllTeacherCourse();
    }

    @GetMapping("teacher/{id}")
    public GetTeacherCourseResponse getCourseById(
            @PathVariable("id") Long id
    ) {
        return teacherCourseService.getTeacherCourseById(id);
    }

    @PostMapping("/{courseId}/teacher/{teacherId}")
    public Object joinTeacherCourse(
            @PathVariable Long courseId,
            @PathVariable Long teacherId
    ) {
        return teacherCourseService.joinTeacherCourse(courseId,teacherId);
    }


    @PutMapping("/{courseId}/teacher/{teacherId}/id/{Id}")
    public Object updateTeacherCourse(
            @PathVariable Long courseId,
            @PathVariable Long teacherId,
            @PathVariable Long Id
    ) {
        return teacherCourseService.updateTeacherCourse(courseId,teacherId,Id);
    }
}
