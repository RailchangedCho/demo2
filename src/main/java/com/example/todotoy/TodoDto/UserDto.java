package com.example.todotoy.TodoDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;
    @NotBlank(message = "Firstname should not be empty")
    private String firstName;
    @NotBlank(message = "Lastname should not be empty")
    private String lastName;
    @NotBlank(message = "Email should not be empty")
    @Email(message = "Enter a valid email address")
    private String email;
    @NotBlank(message = "Password should not be empty")
    @Size(min = 6, message = "Password Length should be longer than 6 characters")
    private String password;
}
