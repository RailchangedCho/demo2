package com.example.todotoy.TodoService.impl;

import com.example.todotoy.TodoDao.TodoDao;
import com.example.todotoy.TodoDto.TodoDto;
import com.example.todotoy.TodoEntity.TodoEntity;
import com.example.todotoy.TodoService.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TodoServiceimpl implements TodoService {
    private final TodoDao todoDao;

    @Autowired
    public TodoServiceimpl(TodoDao todoDao) {
        this.todoDao = todoDao;
    }

    @Override
    public TodoDto saveTask(String id, String task, String due) {
        TodoEntity todoEntity = new TodoEntity();
        todoEntity.setId(id);
        todoEntity.setTask(task);
        todoEntity.setDue(due);
        todoDao.saveTask(todoEntity);
        TodoDto todoDto = new TodoDto(todoEntity.getId(), todoEntity.getTask(),
                todoEntity.getDue());

        return todoDto;
    }

    @Override
    public TodoDto getTask(String id) {
        TodoEntity todoEntity = todoDao.getTask(id);
        TodoDto todoDto = new TodoDto(todoEntity.getId(), todoEntity.getTask(),
                todoEntity.getDue());

        return todoDto;
    }

    @Override
    public TodoDto delTask(String id) {
        TodoEntity todoEntity = todoDao.delTask(id);
        TodoDto todoDto = new TodoDto(todoEntity.getId(), todoEntity.getTask(),
                todoEntity.getDue());

        return todoDto;
    }

    @Override
    public List<TodoEntity> getAllTasks() {
        return todoDao.getAllTasks();
    }

}
