package com.example.todotoy.TodoService;

import com.example.todotoy.TodoDto.TodoDto;
import com.example.todotoy.TodoEntity.TodoEntity;

import java.util.List;

public interface TodoService {
    TodoDto saveTask(String id, String task, String due);
    TodoDto getTask(String id);
    TodoDto delTask(String id);
    List<TodoEntity> getAllTasks();
}
