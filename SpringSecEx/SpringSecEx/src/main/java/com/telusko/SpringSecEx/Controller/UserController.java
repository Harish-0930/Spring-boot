package com.telusko.SpringSecEx.Controller;

import com.telusko.SpringSecEx.Service.MyUserDetailsService;
import com.telusko.SpringSecEx.Service.UserService;
import com.telusko.SpringSecEx.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    private MyUserDetailsService myUserDetailsService;

    @PostMapping("login")
    public String user(@RequestBody Users user){
        return userService.verify(user);

    }


    @PostMapping("/register")
    public Users register(@RequestBody Users user1){
        return userService.save(user1);
    }
}
