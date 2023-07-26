package com.example.todotoy.TodoCtrl;

import com.example.todotoy.TodoDto.UserDto;
import com.example.todotoy.TodoEntity.UserEntity;
import com.example.todotoy.TodoService.LoginService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class LoginCtrl {

    private final LoginService loginService;

    @Autowired
    public LoginCtrl(LoginService loginService) {
        this.loginService = loginService;
    }

    @GetMapping("/index")
    public String showHomepage() {
        return "index";
    }

    @GetMapping("/register")
    public String showRegister(@ModelAttribute UserDto user, Model model){
        // create model object to store form data
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping("/register/save")
    public String register(@Valid @ModelAttribute("user") @RequestBody UserDto userDto,
                               BindingResult result,
                               Model model){

        UserEntity existingUser = loginService.findUserByEmail(userDto.getEmail());

        if(existingUser != null) {
                result.rejectValue("email", "NoneValid email",
                        "There is already an account registered with the same email");
        }

        if(result.hasErrors()){
            model.addAttribute("user", userDto);
            return "/register";
        }

        loginService.saveUser(userDto);
        return "redirect:/register?success";
    }

    @GetMapping("/users")
    public String showUsers(Model model){
        List<UserDto> users = loginService.findAllUsers();
        model.addAttribute("users", users);
        return "users";
    }

    @GetMapping("/login")
    public String showLogin(){
        return "login";
    }
}
