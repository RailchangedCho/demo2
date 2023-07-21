package com.example.todotoy.TodoDao;

import com.example.todotoy.TodoEntity.TodoEntity;

import java.util.List;

public interface TodoDao {
    TodoEntity saveTask(TodoEntity todoEntity);
    TodoEntity getTask(String id);
    TodoEntity delTask(String id);
    List<TodoEntity> getAllTasks();
}
