package com.example.FirstProject.repository;

import com.example.FirstProject.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student,String> {
    Optional <Student> findByIdName(String name);
}
