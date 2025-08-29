package com.habittracker.util;

import com.habittracker.repository.UserRepository;
import com.habittracker.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class UserUtil {

    private final UserRepository userRepository;

    public User getCurrentUser() {
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found with username: " + authentication.getName()));
    }
}
