package com.example.todotoy.DAOTest;


import com.example.todotoy.TodoDao.impl.TodoDaoimpl;
import com.example.todotoy.TodoEntity.TodoEntity;
import com.example.todotoy.TodoRepo.TodoRepo;
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
public class TodoDaoTest {

    @InjectMocks
    private TodoDaoimpl todoDaoimpl;
    @Mock
    private TodoRepo todoRepo;

    @Test
    @DisplayName("## Save Task Test ##")
    void saveTaskTestDAO() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoRepo.save(any())).willReturn(todoEntity);
        TodoEntity returnEntity = todoDaoimpl.saveTask(todoEntity);

        assertEquals(todoEntity.getId(), returnEntity.getId());
        assertEquals(todoEntity.getTask(), returnEntity.getTask());
        assertEquals(todoEntity.getDue(), returnEntity.getDue());
    }

    @Test
    @DisplayName("## Get Task Test ##")
    void getTaskTestDAO() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoRepo.getById("001")).willReturn(todoEntity);
        TodoEntity returnEntity = todoDaoimpl.getTask("001");

        assertEquals(todoEntity.getId(), returnEntity.getId());
        assertEquals(todoEntity.getTask(), returnEntity.getTask());
        assertEquals(todoEntity.getDue(), returnEntity.getDue());
    }

    @Test
    @DisplayName("## Delete Task Test ##")
    void delTaskTestDAO() throws Exception {
        TodoEntity todoEntity = new TodoEntity("001", "Cleaning", "2023-08-01");
        given(todoRepo.getById("001")).willReturn(todoEntity);
        TodoEntity returnEntity = todoDaoimpl.delTask("001");

        assertEquals(todoEntity.getId(), returnEntity.getId());
        assertEquals(todoEntity.getTask(), returnEntity.getTask());
        assertEquals(todoEntity.getDue(), returnEntity.getDue());
    }


    @Test
    @DisplayName("## Get all Tasks Test ##")
    void getAllTasksTestDAO() throws Exception {
        TodoEntity todoEntity1 = new TodoEntity("001", "Cleaning", "2023-08-01");
        TodoEntity todoEntity2 = new TodoEntity("002", "Doing homework", "2023-08-02");
        TodoEntity todoEntity3 = new TodoEntity("003", "Science project", "2023-08-03");
        List<TodoEntity> todolist = new ArrayList<>();
        todolist.add(todoEntity1);
        todolist.add(todoEntity2);
        todolist.add(todoEntity3);
        given(todoRepo.findAll()).willReturn(todolist);

        List<TodoEntity> listEntity = todoDaoimpl.getAllTasks();

        assertEquals(listEntity.get(0), todoEntity1);
        assertEquals(listEntity.get(1), todoEntity2);
        assertEquals(listEntity.get(2), todoEntity3);
    }
}
