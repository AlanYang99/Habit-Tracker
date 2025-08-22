package com.habittracker.service;

import java.util.Map;

public interface IStreakService {
    void resetSteak();
    void updateStreaks();
    Map<String, Long> getUserStreaks();
}
