package com.example.todotoy.CtrlTest;

import com.example.todotoy.TodoCtrl.LoginCtrl;
import com.example.todotoy.TodoDto.UserDto;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoService.impl.LoginServiceimpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(
        controllers = LoginCtrl.class,
        excludeAutoConfiguration = SecurityAutoConfiguration.class
)
public class LoginCtrlTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LoginServiceimpl loginService;

    @Test
    @DisplayName("## Displaying Homepage test ##")
    void showHomepageTest() throws Exception {
        mockMvc.perform(get("/index"))
                .andExpect(status().isOk())
                .andExpect(view().name("index"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Displaying registering test ##")
    void showRegisterTest() throws Exception {
        mockMvc.perform(get("/register?id=1&firstName=Cho&lastName=Dongkuk&email=dongkuk4888@kaist.ac.kr&password=123456"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("register"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Registering test - FAIL : Email Overlapping ##")
    void registerFailTest() throws Exception {
        UserEntity user = new UserEntity();
        user.setId(1L);
        user.setName("Shin Hyosup");
        user.setEmail("dongkuk4888@kaist.ac.kr");
        user.setPassword("123456");

        given(loginService.findUserByEmail("dongkuk4888@kaist.ac.kr")).willReturn(user);

        mockMvc.perform(post("/register/save?id=1&firstName=Cho&lastName=Dongkuk&email=dongkuk4888@kaist.ac.kr&password=123456"))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("user"))
                .andExpect(view().name("/register"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Registering test - SUCCESS ##")
    void registerSuccessTest() throws Exception {
        mockMvc.perform(post("/register/save?id=1&firstName=Cho&lastName=Dongkuk&email=dongkuk4888@kaist.ac.kr&password=123456"))
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:/register?success"))
                .andDo(print());
    }

    @Test
    @DisplayName("## Displaying users test ##")
    void showUsersTest() throws Exception {
        List<UserDto> users = loginService.findAllUsers();
        mockMvc.perform(get("/users"))
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(model().attribute("users", users))
                .andDo(print());
    }

    @Test
    @DisplayName("## Displaying login test ##")
    void showLoginTest() throws Exception {
        mockMvc.perform(get("/login"))
                .andExpect(status().isOk())
                .andExpect(view().name("login"))
                .andDo(print());
    }
}
