package com.habittracker.service.impl;

import com.habittracker.repository.CurrentStreakRepository;
import com.habittracker.repository.LongestStreakRepository;
import com.habittracker.service.IStreakService;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@RequiredArgsConstructor
@Service
public class StreakServiceImpl implements IStreakService {

    private final CurrentStreakRepository currentStreakRepository;
    private final LongestStreakRepository longestStreakRepository;
    private final UserUtil userUtil;

    @Override
    public void resetSteak() {
        // Cron job will do the job of resetting the current streak
    }

    @Override
    public void updateStreaks() {
        final LocalDate longestStreakStartDate = userUtil.getCurrentUser().getLongestStreak().getStartDate();
        final LocalDate longestStreakEndDate = userUtil.getCurrentUser().getLongestStreak().getEndDate();
        final LocalDate currentStreakStartDate = userUtil.getCurrentUser().getCurrentStreak().getStartDate();

        final LocalDate today = LocalDate.now();
        userUtil.getCurrentUser().getCurrentStreak().setEndDate(today);
        if (ChronoUnit.DAYS.between(currentStreakStartDate, today) >= ChronoUnit.DAYS.between(longestStreakStartDate, longestStreakEndDate)) {
            userUtil.getCurrentUser().getLongestStreak().setStartDate(currentStreakStartDate);
            userUtil.getCurrentUser().getLongestStreak().setEndDate(today);
            longestStreakRepository.save(userUtil.getCurrentUser().getLongestStreak());
        }
        currentStreakRepository.save(userUtil.getCurrentUser().getCurrentStreak());
    }
}
