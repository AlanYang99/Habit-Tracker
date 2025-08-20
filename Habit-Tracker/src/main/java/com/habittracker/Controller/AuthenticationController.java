package com.habittracker.Controller;

import com.habittracker.dto.*;
import com.habittracker.entity.User;
import com.habittracker.exception.AccountAlreadyExistsException;
import com.habittracker.service.IUserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<UserRegistrationResponseDto> registerUser(@Valid @RequestBody final UserRegistrationRequestDto userRegistrationDto) {
        try {
            userService.createUser(userRegistrationDto);
        } catch (CompromisedPasswordException e) {
            return ResponseEntity.badRequest().body(new UserRegistrationResponseDto("The provided password is compromised and should not be used.", null));
        } catch (AccountAlreadyExistsException e) {
            return ResponseEntity.badRequest().body(new UserRegistrationResponseDto("An account with the provided username or email already exists", null));
        }
        final UserDto userDto = new UserDto();
        BeanUtils.copyProperties(userRegistrationDto, userDto);

        return ResponseEntity.status(HttpStatus.CREATED).body(new UserRegistrationResponseDto("User registered successfully",userDto));
    }

    @PostMapping("/login")
    public ResponseEntity<UserLoginResponseDto> loginUser(@Valid @RequestBody final UserLoginRequestDto userLoginRequestDto) {
        String jwtToken;
        try {
            jwtToken = userService.loginUser(userLoginRequestDto); // Maybe consider bundling up the JWT token and user details in a response DTO later
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(new UserLoginResponseDto("The provided username and/or password is incorrect", null,null));
        }

        final UserDto userDto = new UserDto();
        final User user = userService.getUser(userLoginRequestDto.getUsername());
        BeanUtils.copyProperties(user, userDto);

        return ResponseEntity.ok().body(new UserLoginResponseDto("The provided password is compromised and should not be used.", jwtToken, userDto));
    }
}
