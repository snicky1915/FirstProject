package com.example.FirstProject.mapper;

import com.example.FirstProject.dto.request.StudentRequest;
import com.example.FirstProject.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    @Mapping(target = "id", ignore = true)
    Student toStudent(StudentRequest studentRequest);

    List<Student> toStudentList(List<StudentRequest> studentRequestList);

    StudentRequest toRequestStudent(Student student);

    List<StudentRequest> toRequestStudentList(List<Student> studentList);
}

