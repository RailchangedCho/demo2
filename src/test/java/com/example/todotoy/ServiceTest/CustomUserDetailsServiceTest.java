package com.example.todotoy.ServiceTest;

import com.example.todotoy.TodoEntity.RoleEntity;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoRepo.UserRepo;
import com.example.todotoy.TodoService.impl.CustomUserDetailsService;
import org.junit.Rule;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.rules.ExpectedException;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.management.relation.Role;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class CustomUserDetailsServiceTest {

    @Rule
    public ExpectedException expectedException = ExpectedException.none();
    @InjectMocks
    private CustomUserDetailsService customUserDetailsService;
    @Mock
    private UserRepo userRepo;

    @Test
    @DisplayName("## Load user by username Test - Non valid user ##")
    public void loadUserByUsernameTestFail() {
        Assertions.assertThrows(UsernameNotFoundException.class, () -> {
            given(userRepo.findByEmail(any())).willReturn(null);
            customUserDetailsService.loadUserByUsername("dongkuk4888@kaist.ac.kr");
        });
    }

    @Test
    @DisplayName("## Load user by username Test - Valid user ##")
    public void loadUserByUsernameTestSuccess() {
        List<UserEntity> userEntityList = new ArrayList<>();
        List<RoleEntity> roleEntityList = new ArrayList<>();
        RoleEntity roleEntity = new RoleEntity(1L, "ROLE_ADMIN", userEntityList);
        roleEntityList.add(roleEntity);
        UserEntity userEntity = new UserEntity(1L, "Cho Dongkuk", "dongkuk4888@kaist.ac.kr", "123456", roleEntityList);
        userEntityList.add(userEntity);
        given(userRepo.findByEmail(any())).willReturn(userEntity);

        UserDetails userDetails = customUserDetailsService.loadUserByUsername("dongkuk4888@kaist.ac.kr");
        assertEquals(userDetails.getUsername(), userEntity.getEmail());
    }

}
