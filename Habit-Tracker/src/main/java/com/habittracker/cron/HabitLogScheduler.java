package com.habittracker.cron;

import com.habittracker.service.IHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class HabitLogScheduler {

    private final IHabitService habitService;

    @Scheduled(cron = "0 0 0 * * *", zone = "Australia/Sydney")
    public void logIncompleteHabits() {
        habitService.markIncompleteHabits();
    }
}
