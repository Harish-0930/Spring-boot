package com.telusko.SpringSecEx.Controller;

import com.telusko.SpringSecEx.Service.MyUserDetailsService;
import com.telusko.SpringSecEx.model.Student;
import com.telusko.SpringSecEx.model.Users;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.csrf.CsrfToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class StudentController {

    @Autowired
    private MyUserDetailsService myUserDetailsService;

    private List<Student> students=new ArrayList<>(List.of(
            new Student(1,"Harish",60),
            new Student(2,"Kiran",65)
    ));


    @GetMapping("/students")
    public List<Student>getStudents(){
        return students;
    }

    @GetMapping("/csrf-token")
    public CsrfToken getCsrfToken(HttpServletRequest request){
        return (CsrfToken) request.getAttribute("_csrf");

    }
    @PostMapping("students")
    public Student addStudent(@RequestBody Student student){
        students.add(student);
        return student;
    }


}
