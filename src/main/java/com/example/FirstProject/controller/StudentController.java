package com.example.FirstProject.controller;

import com.example.FirstProject.dto.request.StudentRequest;
import com.example.FirstProject.entity.Student;
import com.example.FirstProject.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
public class StudentController{
    @Autowired
    private StudentService studentService;
    @PostMapping()
    public Student createStudent(@RequestBody StudentRequest studentRequest){
        return studentService.createStudent(studentRequest);
    }
//    @PostMapping
//    public Student updateStudent(@RequestBody StudentRequest studentRequest){
//        return studentService.updateStudent(studentRequest);
//    }
    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable String id){
        studentService.deleteStudentbyId(id);
    }


}
