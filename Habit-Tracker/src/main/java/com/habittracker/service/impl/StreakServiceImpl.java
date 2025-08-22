package com.habittracker.service.impl;

import com.habittracker.entity.AbstractStreak;
import com.habittracker.entity.CurrentStreak;
import com.habittracker.entity.LongestStreak;
import com.habittracker.entity.User;
import com.habittracker.repository.CurrentStreakRepository;
import com.habittracker.repository.LongestStreakRepository;
import com.habittracker.repository.StreakRepository;
import com.habittracker.service.IStreakService;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.HashMap;
import java.util.Map;

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

    @Override
    public Map<String, Long> getUserStreaks() {
        final Map<String, Long> streakMapping = new HashMap<String, Long>();
        final User currentUser = userUtil.getCurrentUser();

        streakMapping.put("Current Streak", getStreakDuration(currentUser, currentUser.getCurrentStreak(), currentStreakRepository));
        streakMapping.put("Longest Streak", getStreakDuration(currentUser, currentUser.getLongestStreak(), longestStreakRepository));

        return streakMapping;
    }

    private Long getStreakDuration(final User user, final AbstractStreak streak, final StreakRepository streakRepository) {
        if (streak != null && Hibernate.isInitialized(streak)) {
            return ChronoUnit.DAYS.between(streak.getStartDate(), streak.getEndDate());
        }

        AbstractStreak persistentStreak = streakRepository.findByUserId(user.getId());
        return ChronoUnit.DAYS.between(persistentStreak.getStartDate(), persistentStreak.getEndDate());
    }
}
