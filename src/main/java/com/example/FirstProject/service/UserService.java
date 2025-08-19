package com.example.FirstProject.service;

import com.example.FirstProject.entity.User;
import com.example.FirstProject.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public User create(User requestUser){
        return userRepository.save(requestUser);
    }

}
