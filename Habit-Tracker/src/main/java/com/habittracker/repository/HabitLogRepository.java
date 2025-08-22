package com.habittracker.repository;

import com.habittracker.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
    boolean existsByHabitIdAndCreationDate(final Long habitId, final LocalDate date);
    List<HabitLog> findByUserId(final Long userId);
}
