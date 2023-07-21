package com.example.todotoy.TodoDao.impl;


import com.example.todotoy.TodoDao.TodoDao;
import com.example.todotoy.TodoEntity.TodoEntity;
import com.example.todotoy.TodoRepo.TodoRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoDaoimpl implements TodoDao {
    private final TodoRepo todoRepo;

    @Autowired
    public TodoDaoimpl(TodoRepo todoRepo) {
        this.todoRepo = todoRepo;
    }

    @Override
    public TodoEntity saveTask(TodoEntity todoEntity) {
        todoRepo.save(todoEntity);
        return todoEntity;
    }

    @Override
    public TodoEntity getTask(String id) {
        return todoRepo.getById(id);
    }

    @Override
    public TodoEntity delTask(String id) {
        TodoEntity todoEntity = todoRepo.getById(id);
        todoRepo.delete(todoEntity);
        return todoEntity;
    }

    @Override
    public List<TodoEntity> getAllTasks() {
        return todoRepo.findAll();
    }

}
