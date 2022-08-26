package Course.api;

import Course.request.CreateCourseRequest;
import Course.request.CreateStudentRequest;
import Course.response.GetCourseResponse;
import Course.response.GetStudentResponse;
import Course.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseApi {
    @Autowired
    private CourseService courseService;


    @GetMapping("/all")
    public List<GetCourseResponse> getAllCourse() {
        return courseService.getAllCourse();
    }

    @GetMapping("/{id}")
    public GetCourseResponse getCourseById(
            @PathVariable("id") Long id
    ) {
        return courseService.getCourseById(id);
    }

    @PostMapping("/create")
    public GetCourseResponse createCourse(
            @RequestBody CreateCourseRequest request
    ) {
        return courseService.createCourse(request);
    }



    @PutMapping("/update/{id}")
    public GetCourseResponse updateCourse(
            @RequestBody CreateCourseRequest request,
            @PathVariable Long id
    ) {
        return courseService.updateCourse(request, id);
    }
}
