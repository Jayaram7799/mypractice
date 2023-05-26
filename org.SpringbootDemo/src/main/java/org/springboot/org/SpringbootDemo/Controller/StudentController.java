package org.springboot.org.SpringbootDemo.Controller;

import java.util.List;

import org.springboot.org.SpringbootDemo.Services.StudentService;
import org.springboot.org.SpringbootDemo.dto.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
@RestController
public class StudentController {
 
	@Autowired
    private StudentService studentService;

	@GetMapping("/students")
    public List<Student> getPaginatedStudents(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize
    ) {
        return studentService.getPaginatedStudents(page, pageSize);
    }

    @GetMapping("/filter")
    public List<Student> filterStudents(
            @RequestParam String filterCriteria
    ) {
        return studentService.filterStudents(filterCriteria);
    }
}
