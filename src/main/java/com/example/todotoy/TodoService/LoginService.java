package com.example.todotoy.TodoService;

import com.example.todotoy.TodoDto.UserDto;
import com.example.todotoy.TodoEntity.UserEntity;

import java.util.List;

public interface LoginService {
    UserEntity saveUser(UserDto userDto);

    UserEntity findUserByEmail(String email);

    List<UserDto> findAllUsers();

    List<UserEntity> getAllUsers();
}
