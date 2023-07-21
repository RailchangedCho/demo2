package com.example.todotoy.CtrlTest;

import com.example.todotoy.TodoCtrl.TodoCtrl;
import com.example.todotoy.TodoEntity.TodoEntity;
import com.example.todotoy.TodoService.impl.TodoServiceimpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = TodoCtrl.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class TodoCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TodoServiceimpl todoService;

    @Test
    @DisplayName("## Displaying tasks test ##")
    void listTaskTest() throws Exception {
        List<TodoEntity> task = todoService.getAllTasks();
        mockMvc.perform(get("/tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("todotoy"))
                .andExpect(model().attribute("tasks", task))
                .andDo(print());
    }

    @Test
    @DisplayName("## Add new task test ##")
    void emptyTaskTest() throws Exception {
        mockMvc.perform(get("/tasks/new"))
                .andExpect(model().attributeExists("tasks"))
                .andExpect(status().isOk())
                .andExpect(view().name("create_tasks"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Adding Task test - FAIL ##")
    void saveTaskFailTest() throws Exception {
        mockMvc.perform(post("/tasks/post?id=&task=Todo list making&due=2023-07-09"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("tasks"))
                .andExpect(view().name("create_tasks"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Adding Task test - SUCCESS ##")
    void saveTaskSuccessTest() throws Exception {
        mockMvc.perform(post("/tasks/post?id=001&task=Todo list making&due=2023-07-09"))
                .andExpect(status().is3xxRedirection())
                .andExpect(model().attributeDoesNotExist("tasks"))
                .andExpect(view().name("redirect:/tasks"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Deleting Task test ##")
    void delTaskTest() throws Exception {
        mockMvc.perform(get("/tasks/001"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/tasks"))
                .andDo(print());
    }

    // TODO: 2023-07-07
}
