package com.telusko.SpringSecEx.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping("/")
    public String Greet(HttpServletRequest request){
        return "Hello i am harish "+request.getSession().getId();
    }
}
