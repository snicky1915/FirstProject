package com.example.FirstProject.service;

import com.example.FirstProject.dto.request.StudentRequest;
import com.example.FirstProject.entity.Student;
import com.example.FirstProject.mapper.StudentMapper;
import com.example.FirstProject.repository.StudentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private StudentMapper studentMapper;

    public List<Student> getAllStudent(){
        return studentRepository.findAll();
    }
    public Student createStudent(StudentRequest studentRequest){
        Student student = studentMapper.toStudent(studentRequest);
        return studentRepository.save(student);
    }
    public Student updateStudent(String id,StudentRequest studentRequest) {
        Student existingStudent = studentRepository.findById(id).orElseThrow(() -> new RuntimeException("Student with ID " + studentRequest.getId() + " not found in DB"));
        existingStudent = studentMapper.toStudent(studentRequest);
        return studentRepository.save(existingStudent);
    }
    public void deleteStudentbyId(String id){
        studentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Student with ID " + id +  " not found in DB"));
        studentRepository.deleteById(id);
    }
    public Student getStudentByName(String name){
        return studentRepository.findByIdName(name).orElseThrow(()->new RuntimeException("Cant find name: "+ name+"in DB"));
    }
}
