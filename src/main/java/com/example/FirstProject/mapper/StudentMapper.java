package com.example.FirstProject.mapper;
import com.example.FirstProject.dto.request.StudentRequest;
import com.example.FirstProject.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")

public interface StudentMapper {
    Student toStudent(StudentRequest studentRequest);
}
