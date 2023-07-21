package com.example.todotoy.TodoCtrl;

import com.example.todotoy.TodoDto.TodoDto;
import com.example.todotoy.TodoService.TodoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class TodoCtrl {
    private final TodoService todoService;

    @Autowired
    public TodoCtrl(TodoService todoService) {
        this.todoService = todoService;
    }

    @RequestMapping(value = "/tasks")
    public String listTask(Model model) {
        model.addAttribute("tasks", todoService.getAllTasks());
        return "todotoy";
    }

    @GetMapping("/tasks/new")
    public String emptyTask(Model model) {
        TodoDto todoDto = new TodoDto();
        model.addAttribute("tasks", todoDto);
        return "create_tasks";
    }

    @PostMapping(value = "/tasks/post")
    public String saveTask(@Valid @ModelAttribute("tasks") @RequestBody TodoDto todoDto,
                           BindingResult result,
                           Model model) {
        if(result.hasErrors()){
            model.addAttribute("tasks", todoDto);
            return "create_tasks";
        }
        todoService.saveTask(todoDto.getId(), todoDto.getTask(), todoDto.getDue());
        return "redirect:/tasks";
    }

    @GetMapping("/tasks/{id}")
    public String delTask(@PathVariable String id) {
        todoService.delTask(id);
        return "redirect:/tasks";
    }
 }
