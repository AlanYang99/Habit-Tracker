package com.habittracker.service.impl;

import com.habittracker.Repository.HabitRepository;
import com.habittracker.Repository.UserRepository;
import com.habittracker.entity.AbstractHabit;
import com.habittracker.entity.DailyHabit;
import com.habittracker.entity.User;
import com.habittracker.service.IHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements IHabitService {

    private final UserRepository userRepository;
    private final HabitRepository habitRepository;

    @Override
    public void createHabit(final String habitName, final String description) {
        final User user = new User();
        user.setUsername("testUser");
        user.setEmail("test@email.com");
        user.setPassword("$2a$12$Haat2ftfS5xByaZGvAfqguR5Ffhxxs5gJmLA3Kxx7jk34AU98wR92");
        user.setName("pass1");
        userRepository.save(user);
        final AbstractHabit habit = new DailyHabit();
        habit.setUser(user);
        habit.setName("Exercise");
        habit.setDescription("Daily morning workout");
        habitRepository.save(habit);
//        habit.setUser(user);
    }
}
