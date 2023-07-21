package com.example.todotoy.TodoRepo;

import com.example.todotoy.TodoEntity.TodoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepo extends JpaRepository<TodoEntity, String> {

}
