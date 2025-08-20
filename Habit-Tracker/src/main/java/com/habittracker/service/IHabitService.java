package com.habittracker.service;

import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.AbstractHabit;

public interface IHabitService {
    AbstractHabit createHabit(final HabitRequestDto habitRequestDto);
}
