package com.habittracker.security;

import com.habittracker.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class HabitUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        System.out.println(userRepository.findAll());

        return userRepository.findByUsername(username).map(HabitUserDetails::new).orElseThrow(() -> new UsernameNotFoundException("User not found with username: " + username));
    }
}
