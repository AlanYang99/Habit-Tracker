package com.habittracker.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class UserRegistrationRequestDto {

    @NotBlank(message = "Username is required")
    @Size(min = 8, max = 30, message = "Username must be between 8 and 30 characters")
    private String username;

    @NotBlank(message = "Password is required")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;

    @NotBlank(message = "Email is required")
    @Email(message = "Email address must be a valid value")
    private String email;

    @NotBlank(message = "Name is required")
    private String name;
}
