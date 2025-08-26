package com.example.FirstProject.repository;

import com.example.FirstProject.common.BaseRepository;
import com.example.FirstProject.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User,Long> {
    Optional<User> findByUsername(String name);
    Optional<User> findByEmail(String email);
}