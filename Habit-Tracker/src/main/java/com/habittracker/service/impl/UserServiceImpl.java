package com.habittracker.service.impl;

import com.habittracker.dto.SimpleUserDto;
import com.habittracker.dto.UserDto;
import com.habittracker.entity.CurrentStreak;
import com.habittracker.entity.GameStats;
import com.habittracker.entity.LongestStreak;
import com.habittracker.mapper.UserMapper;
import com.habittracker.repository.*;
import com.habittracker.dto.UserLoginRequestDto;
import com.habittracker.dto.UserRegistrationRequestDto;
import com.habittracker.entity.User;
import com.habittracker.exception.AccountAlreadyExistsException;
import com.habittracker.security.HabitUserDetails;
import com.habittracker.service.IStreakService;
import com.habittracker.service.IUserService;
import com.habittracker.util.JwtUtil;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.security.authentication.password.CompromisedPasswordChecker;
import org.springframework.security.authentication.password.CompromisedPasswordException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements IUserService {

    private final CompromisedPasswordChecker compromisedPasswordChecker;
    private final PasswordEncoder passwordEncoder;
    private final IStreakService streakService;
    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final GameStatRepository gameStatRepository;
    private final CurrentStreakRepository currentStreakRepository;
    private final LongestStreakRepository longestStreakRepository;
    private final UserUtil userUtil;
    private final JwtUtil jwtUtil;


    public UserDto createUser(final UserRegistrationRequestDto userRegistrationRequestDto) {
        if (userRepository.findByUsername(userRegistrationRequestDto.getUsername()).isPresent() || userRepository.findByEmail(userRegistrationRequestDto.getEmail()).isPresent()) {
            throw new AccountAlreadyExistsException();
        }

        if (compromisedPasswordChecker.check(userRegistrationRequestDto.getPassword()).isCompromised()) {
            throw new CompromisedPasswordException("The provided password is compromised and should not be used.");
        }

        final User user = generateUser(userRegistrationRequestDto);

        return userMapper.newUserDto(user);
    }

    public String loginUser(final UserLoginRequestDto userLoginRequestDto) {
        final User user = userRepository.findByUsername(userLoginRequestDto.getUsername())
                .filter(account -> passwordEncoder.matches(userLoginRequestDto.getPassword(), account.getPassword()))
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password"));

        return jwtUtil.generateToken(new HabitUserDetails(user));
    }

    public UserDto getUserSummary() {
        final User user = userUtil.getCurrentUser();
        final Map<String, Long> userStreak = streakService.getUserStreaks();

        return userMapper.mapToDto(user, userStreak);
    }

    public SimpleUserDto getSimpleUserSummary(final UserLoginRequestDto userLoginRequestDto) {
        final User user = getUser(userLoginRequestDto.getUsername());
        return userMapper.mapToSimpleDto(user);
    }

    private User generateUser(final UserRegistrationRequestDto userRegistrationRequestDto) {
        final User user = new User();
        BeanUtils.copyProperties(userRegistrationRequestDto, user);
        user.setPassword(passwordEncoder.encode(userRegistrationRequestDto.getPassword()));

        final GameStats stats = new GameStats();
        stats.setUser(user);
        stats.setUserExp(0);
        stats.setUserLevel(1);

        final LocalDate today = LocalDate.now();
        final CurrentStreak currentStreak = new CurrentStreak();
        currentStreak.setUser(user);
        currentStreak.setStartDate(today);
        currentStreak.setEndDate(today);

        final LongestStreak longestStreak = new LongestStreak();
        longestStreak.setUser(user);
        longestStreak.setStartDate(today);
        longestStreak.setEndDate(today);

        userRepository.save(user);
        gameStatRepository.save(stats);
        currentStreakRepository.save(currentStreak);
        longestStreakRepository.save(longestStreak);

        return user;
    }

    private User getUser(final String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found with username: " + username));
    }
}
