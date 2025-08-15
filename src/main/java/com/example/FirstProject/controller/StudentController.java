package com.example.FirstProject.controller;

import com.example.FirstProject.dto.request.StudentRequest;
import com.example.FirstProject.entity.Student;
import com.example.FirstProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentService studentService;
    @PostMapping()
    public Student createStudent(@RequestBody StudentRequest studentRequest){
        return studentService.createStudent(studentRequest);
    }
    @PutMapping("/{id}")
    public Student updateStudent(@PathVariable String id,@RequestBody StudentRequest studentRequest){
        return studentService.updateStudent(id,studentRequest);
    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        studentService.deleteStudentbyId(id);
    }
    @GetMapping
    public List<Student> getAllStudent(){
        return studentService.getAllStudent();
    }
    @GetMapping("/{name}")
    public Student getStudentByName(@PathVariable String name){
        return studentService.getStudentByName(name);
    }
    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public String handleHelloMessage(String message) {
        System.out.println("Nhận được tin nhắn: " + message);
        return "Server trả lời: " + message;
    }
}
