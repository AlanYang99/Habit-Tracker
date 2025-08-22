package com.habittracker.service;

import com.habittracker.entity.HabitLog;

import java.util.List;

public interface IHabitLogService {
    List<HabitLog> getAllHabitLogs();
}
