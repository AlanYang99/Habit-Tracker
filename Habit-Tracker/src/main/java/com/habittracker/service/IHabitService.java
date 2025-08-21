package com.habittracker.service;

import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.AbstractHabit;

import java.util.List;
import java.util.Map;

public interface IHabitService {
    AbstractHabit createHabit(final HabitRequestDto habitRequestDto);
    List<AbstractHabit> getAllHabitsForCurrentUser(final Map<String, String> params);
    void completeHabit(final Long habitId);
}
