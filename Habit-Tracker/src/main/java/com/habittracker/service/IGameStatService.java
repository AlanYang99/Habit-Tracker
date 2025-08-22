package com.habittracker.service;

import com.habittracker.entity.GameStats;

public interface IGameStatService {
    void updateGameStatsOnHabitCompletion();
    GameStats getGameStatForCurrentUser();
}
