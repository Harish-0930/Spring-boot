package com.course.thymleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class MainController {
    @GetMapping("/home")
    public String loadHome(Model model)  {
        model.addAttribute("name","Harish");
        return "home";
    }


    @GetMapping("/elvis")
    public String elvisExample(Model model){
        model.addAttribute("isAdmin",true);
        model.addAttribute("gender","female");
        return "elvis";
    }
    /*
    -> Thymeleaf Conditional statements
    1. Elvis operator: it is similar to conditional statement(ternary operator) in java
    2. If unless
    3. Switch case
     */

    @GetMapping("/each")
    public String each(Model model){
        List<String> stringList = List.of("First","Second","Third");
        model.addAttribute("list", stringList);
        return "each";
    }

}
