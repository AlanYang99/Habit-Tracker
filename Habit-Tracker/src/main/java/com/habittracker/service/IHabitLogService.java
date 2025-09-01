package com.habittracker.service;

import com.habittracker.dto.HabitLogDto;
import com.habittracker.entity.HabitLog;

import java.util.List;

public interface IHabitLogService {
    List<HabitLogDto> getAllHabitLogs();
}
