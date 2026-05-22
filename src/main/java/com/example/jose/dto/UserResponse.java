package com.example.jose.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {

    @NotEmpty(message = "Name cannot be empty")
    private String name;
    @NotEmpty(message = "email cannot be empty")
    @Email(message = "email invalid")
    private String email;
    @NotEmpty(message = "password cannot be empty")
    @Size(min = 8, message = " Password must be at least 8 characters")
    private String password;
}
