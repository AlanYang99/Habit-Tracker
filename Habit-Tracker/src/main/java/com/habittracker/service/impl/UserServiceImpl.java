package com.habittracker.service.impl;

import com.habittracker.repository.UserRepository;
import com.habittracker.dto.UserLoginRequestDto;
import com.habittracker.dto.UserRegistrationRequestDto;
import com.habittracker.entity.User;
import com.habittracker.exception.AccountAlreadyExistsException;
import com.habittracker.security.HabitUserDetails;
import com.habittracker.service.IUserService;
import com.habittracker.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;
    private final JwtUtil jwtUtil;

    public void createUser(final UserRegistrationRequestDto userRegistrationRequestDto) {
        if (compromisedPasswordChecker.check(userRegistrationRequestDto.getPassword()).isCompromised()) {
            throw new CompromisedPasswordException("The provided password is compromised and should not be used.");
        }

        if (userRepository.findByUsername(userRegistrationRequestDto.getUsername()).isPresent() || userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new AccountAlreadyExistsException();
        }

        final User user = new User();
        BeanUtils.copyProperties(userRegistrationRequestDto, user);
        user.setPassword(passwordEncoder.encode(userRegistrationRequestDto.getPassword()));
        userRepository.save(user);
    }

    public String loginUser(final UserLoginRequestDto userLoginRequestDto) {
        final User user = userRepository.findByUsername(userLoginRequestDto.getUsername())
                .filter(account -> passwordEncoder.matches(userLoginRequestDto.getPassword(), account.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        return jwtUtil.generateToken(new HabitUserDetails(user));
    }

    public User getUser(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }
}
