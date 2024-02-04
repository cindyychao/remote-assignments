package com.example.Assignment3.repository;

import com.example.Assignment3.entity.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    boolean existsByEmail(String email);
    User findByEmailAndPassword(String email, String password);
}