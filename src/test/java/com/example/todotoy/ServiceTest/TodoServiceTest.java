package com.example.todotoy.ServiceTest;

import com.example.todotoy.TodoDao.TodoDao;
import com.example.todotoy.TodoDto.TodoDto;
import com.example.todotoy.TodoEntity.TodoEntity;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoService.TodoService;
import com.example.todotoy.TodoService.impl.TodoServiceimpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class TodoServiceTest {

    @InjectMocks
    private TodoServiceimpl todoServiceimpl;
    @Mock
    private TodoDao todoDao;

    @Test
    @DisplayName("## Save Task Test ##")
    void saveTaskTest() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoDao.saveTask(any())).willReturn(todoEntity);
        TodoDto todoDto = todoServiceimpl.saveTask("001", "Cleaning", "2023-08-01");

        assertEquals(todoEntity.getId(), todoDto.getId());
        assertEquals(todoEntity.getTask(), todoDto.getTask());
        assertEquals(todoEntity.getDue(), todoDto.getDue());
    }

    @Test
    @DisplayName("## Get Task Test ##")
    void getTaskTest() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoDao.getTask("001")).willReturn(todoEntity);
        TodoDto todoDto = todoServiceimpl.getTask("001");

        assertEquals(todoEntity.getId(), todoDto.getId());
        assertEquals(todoEntity.getTask(), todoDto.getTask());
        assertEquals(todoEntity.getDue(), todoDto.getDue());
    }

    @Test
    @DisplayName("## Delete Task Test ##")
    void delTaskTest() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoDao.delTask("001")).willReturn(todoEntity);
        TodoDto todoDto = todoServiceimpl.delTask("001");

        assertEquals(todoEntity.getId(), todoDto.getId());
        assertEquals(todoEntity.getTask(), todoDto.getTask());
        assertEquals(todoEntity.getDue(), todoDto.getDue());
    }

    @Test
    @DisplayName("## Get all Tasks Test ##")
    void getAllTasksTest() throws Exception {
        TodoEntity todoEntity1 = new TodoEntity("001", "Cleaning", "2023-08-01");
        TodoEntity todoEntity2 = new TodoEntity("002", "Doing homework", "2023-08-02");
        TodoEntity todoEntity3 = new TodoEntity("003", "Science project", "2023-08-03");
        List<TodoEntity> todolist = new ArrayList<>();
        todolist.add(todoEntity1);
        todolist.add(todoEntity2);
        todolist.add(todoEntity3);
        given(todoDao.getAllTasks()).willReturn(todolist);

        List<TodoEntity> listEntity = todoServiceimpl.getAllTasks();

        assertEquals(listEntity.get(0), todoEntity1);
        assertEquals(listEntity.get(1), todoEntity2);
        assertEquals(listEntity.get(2), todoEntity3);
    }
}
