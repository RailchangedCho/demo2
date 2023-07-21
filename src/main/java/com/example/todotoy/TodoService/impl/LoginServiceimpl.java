package com.example.todotoy.TodoService.impl;

import com.example.todotoy.TodoDto.UserDto;
import com.example.todotoy.TodoEntity.RoleEntity;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoRepo.RoleRepo;
import com.example.todotoy.TodoRepo.UserRepo;
import com.example.todotoy.TodoService.LoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LoginServiceimpl implements LoginService {
    private final UserRepo userRepo;
    private final RoleRepo roleRepo;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public LoginServiceimpl(UserRepo userRepo, RoleRepo roleRepo, PasswordEncoder passwordEncoder) {
        this.userRepo = userRepo;
        this.roleRepo = roleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public UserEntity saveUser(UserDto userDto) {
        UserEntity userEntity = new UserEntity();
        userEntity.setId(1L);
        userEntity.setName(userDto.getFirstName() + " " + userDto.getLastName());
        userEntity.setEmail(userDto.getEmail());
        userEntity.setPassword(passwordEncoder.encode(userDto.getPassword()));

        RoleEntity roleEntity = roleRepo.findByName("ROLE_ADMIN");
        if(roleEntity == null) { roleEntity = checkRoleExist(); }
        userEntity.setRoles(Arrays.asList(roleEntity));
        userRepo.save(userEntity);
        return userEntity;
    }

    @Override
    public UserEntity findUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        List<UserEntity> users = userRepo.findAll();
        return users.stream()
                .map((user) -> mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public List<UserEntity> getAllUsers() {
        return userRepo.findAll();
    }

    private UserDto mapToUserDto(UserEntity user){
        UserDto userDto = new UserDto();
        String[] str = user.getName().split(" ");
        userDto.setFirstName(str[0]);
        userDto.setLastName(str[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private RoleEntity checkRoleExist(){
        RoleEntity roleEntity = new RoleEntity();
        roleEntity.setName("ROLE_ADMIN");
        return roleRepo.save(roleEntity);
    }
}
