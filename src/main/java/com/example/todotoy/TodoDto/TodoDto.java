package com.example.todotoy.TodoDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TodoDto {
    @NotBlank(message = "You should notify ID of task")
    @Pattern(regexp = "[0-9]{3}", message = "ID form : 000 ~ 999")
    private String id;
    @NotBlank(message = "You should notify DETAILS of task")
    private String task;
    @NotBlank(message = "You should notify DUE of task")
    @Pattern(regexp = "[0-9]{4}-[0-9]{2}-[0-9]{2}", message = "DUE form : yyyy-mm-dd")
    private String due;

}
