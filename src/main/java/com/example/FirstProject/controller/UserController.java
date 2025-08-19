package com.example.FirstProject.controller;

import com.example.FirstProject.entity.User;
import com.example.FirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User requestUser){
        return userService.create(requestUser);
    }

    @PostMapping("/adfadfad")
    public String test(String a){
        return a;
    }

}
