package com.example.todotoy.TodoRepo;

import com.example.todotoy.TodoEntity.RoleEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepo extends JpaRepository<RoleEntity, Long> {
    RoleEntity findByName(String name);
}
