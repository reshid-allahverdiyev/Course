package Course.api;

import Course.entity.StudentEntity;
import Course.request.CreateStudentRequest;
import Course.request.LoginRequest;
import Course.request.SearchStudentRequest;
import Course.response.*;
import Course.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/student")
public class  StudentApi {

    @Autowired
    private StudentService studentService;


    @GetMapping("/search")
    public SearchStudentResponse search(
            @RequestBody SearchStudentRequest request,
            @RequestParam(value = "page",required = false,defaultValue = "0") int page,
            @RequestParam(value = "size",required = false,defaultValue = "3") int size
    ) {
        return studentService.getAllStudent(request,page,size);
    }

    @GetMapping("/{id}")
    public GetOneStudentViewResponse getStudentById(
            @PathVariable("id") Long id
    ) {
        return studentService.getStudentById(id);
    }

    @PostMapping("/create")
    public GetStudentResponse createStudent(
            @RequestBody CreateStudentRequest request
    ) {
        return studentService.createStudent(request);
    }

    @PutMapping("/update/{id}")
    public GetStudentResponse updateStudent(
            @RequestBody CreateStudentRequest request,
            @PathVariable Long id
    ) {
        return studentService.updateStudent(request, id);
    }

}
