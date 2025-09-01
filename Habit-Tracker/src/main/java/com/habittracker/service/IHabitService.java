package com.habittracker.service;

import com.habittracker.dto.DueHabitDto;
import com.habittracker.dto.HabitDto;
import com.habittracker.dto.HabitRequestDto;

import java.util.List;
import java.util.Map;

public interface IHabitService {
    HabitDto createHabit(final HabitRequestDto habitRequestDto);
    List<HabitDto> getAllHabitsForCurrentUser(final Map<String, String> params);
    List<DueHabitDto> getDueHabits(final Map<String, String> params);
    void completeHabit(final Long habitId);
    void markIncompleteHabits();
}
