package com.example.todotoy.TodoRepo;

import com.example.todotoy.TodoEntity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepo extends JpaRepository<UserEntity, Long> {
    UserEntity findByEmail(String email);
}
