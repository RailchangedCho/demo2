package com.example.todotoy.ServiceTest;

import com.example.todotoy.TodoDto.UserDto;
import com.example.todotoy.TodoEntity.RoleEntity;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoRepo.RoleRepo;
import com.example.todotoy.TodoRepo.UserRepo;
import com.example.todotoy.TodoService.impl.LoginServiceimpl;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class LoginServiceTest {
    @InjectMocks
    private LoginServiceimpl loginServiceimpl;
    @Mock
    private UserRepo userRepo;
    @Mock
    private RoleRepo roleRepo;
    @Mock
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("## Save user Test 1 - Null RoleEntity ##")
    void saveUserTest() throws Exception {

        given(passwordEncoder.encode("123456")).willReturn("7891011");
        UserDto userDto = new UserDto(1L, "Cho", "Dongkuk", "dongkuk4888@kaist.ac.kr", "123456");

        UserEntity userEntity = loginServiceimpl.saveUser(userDto);

        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getName(), userDto.getFirstName() + " " + userDto.getLastName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getPassword(), "7891011");
    }

    @Test
    @DisplayName("## Save user Test 2 - NotNull RoleEntity ##")
    void saveUserTest2() throws Exception {

        given(passwordEncoder.encode("123456")).willReturn("7891011");
        given(roleRepo.findByName("ROLE_ADMIN")).willReturn(new RoleEntity());
        UserDto userDto = new UserDto(1L, "Cho", "Dongkuk", "dongkuk4888@kaist.ac.kr", "123456");

        UserEntity userEntity = loginServiceimpl.saveUser(userDto);

        assertEquals(userEntity.getId(), userDto.getId());
        assertEquals(userEntity.getName(), userDto.getFirstName() + " " + userDto.getLastName());
        assertEquals(userEntity.getEmail(), userDto.getEmail());
        assertEquals(userEntity.getPassword(), "7891011");
    }

    @Test
    @DisplayName("## Find user by email Test ##")
    void findUserByEmailTest() throws Exception {

        UserEntity userEntity = new UserEntity(1L, "Cho Dongkuk", "dongkuk4888@kaist.ac.kr", "123456", null);
        given(userRepo.findByEmail("dongkuk4888@kaist.ac.kr")).willReturn(userEntity);

        UserEntity returnEntity = loginServiceimpl.findUserByEmail("dongkuk4888@kaist.ac.kr");

        assertEquals(userEntity.getId(), returnEntity.getId());
        assertEquals(userEntity.getName(), returnEntity.getName());
        assertEquals(userEntity.getEmail(), returnEntity.getEmail());
        assertEquals(userEntity.getPassword(), returnEntity.getPassword());
    }

    @Test
    @DisplayName("## Find all users Test ##")
    void findAllUsersTest() throws Exception {
        UserEntity userEntity1 = new UserEntity(1L, "Cho Dongkuk", "dongkuk4888@kaist.ac.kr", "123456", null);
        UserEntity userEntity2 = new UserEntity(2L, "Shin Hyosup", "SH@kaist.ac.kr", "123456", null);
        UserEntity userEntity3 = new UserEntity(3L, "Jung Jihun", "GenChovy@kaist.ac.kr", "123456", null);
        List<UserEntity> userlist = new ArrayList<>();
        userlist.add(userEntity1);
        userlist.add(userEntity2);
        userlist.add(userEntity3);
        given(userRepo.findAll()).willReturn(userlist);

        List<UserDto> listEntity = loginServiceimpl.findAllUsers();

        assertEquals(listEntity.get(0).getEmail(), userEntity1.getEmail());
        assertEquals(listEntity.get(1).getEmail(), userEntity2.getEmail());
        assertEquals(listEntity.get(2).getEmail(), userEntity3.getEmail());
    }

    @Test
    @DisplayName("## Get all users Test ##")
    void getAllUsersTest() throws Exception {
        UserEntity userEntity1 = new UserEntity(1L, "Cho Dongkuk", "dongkuk4888@kaist.ac.kr", "123456", null);
        UserEntity userEntity2 = new UserEntity(2L, "Shin Hyosup", "SH@kaist.ac.kr", "123456", null);
        UserEntity userEntity3 = new UserEntity(3L, "Jung Jihun", "GenChovy@kaist.ac.kr", "123456", null);
        List<UserEntity> userlist = new ArrayList<>();
        userlist.add(userEntity1);
        userlist.add(userEntity2);
        userlist.add(userEntity3);
        given(userRepo.findAll()).willReturn(userlist);

        List<UserEntity> listEntity = loginServiceimpl.getAllUsers();

        assertEquals(listEntity.get(0), userEntity1);
        assertEquals(listEntity.get(1), userEntity2);
        assertEquals(listEntity.get(2), userEntity3);
    }

}
