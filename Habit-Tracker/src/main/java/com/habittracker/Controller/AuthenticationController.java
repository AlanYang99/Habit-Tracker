package com.habittracker.Controller;

import com.habittracker.dto.UserLoginRequestDto;
import com.habittracker.dto.UserRegistrationRequestDto;
import com.habittracker.exception.AccountAlreadyExistsException;
import com.habittracker.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final IUserService userService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(final @RequestBody UserRegistrationRequestDto userRegistrationDto) {
        try {
            userService.createUser(userRegistrationDto);
        } catch (CompromisedPasswordException e) {
            return ResponseEntity.badRequest().body("The provided password is compromised and should not be used.");
        } catch (AccountAlreadyExistsException e) {
            return ResponseEntity.badRequest().body("An account with this email or username already exists.");
        }

        return ResponseEntity.ok("User registered successfully");
    }

    @PostMapping("/login")
    public ResponseEntity<String> loginUser(final @RequestBody UserLoginRequestDto userLoginRequestDto) {
        try {

        }
    }
}
