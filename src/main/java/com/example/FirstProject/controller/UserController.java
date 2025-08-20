package com.example.FirstProject.controller;

import com.example.FirstProject.entity.User;
import com.example.FirstProject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public User createUser(@RequestBody User user){
        return userService.createUser(user);
    }
    public String deleteUser(@PathVariable Long id){
        boolean delete = userService.deleteUser(id);
        if(delete){
            return "User with id" + id + "deleted";
        } else {
            return "User not found";
        }
    }
}
