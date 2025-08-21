package com.habittracker.repository;

import com.habittracker.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    boolean existsByHabitIdAndDate(final Long habitId, final LocalDate date);
}
