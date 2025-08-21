package com.habittracker.repository;

import com.habittracker.entity.LongestStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LongestStreakRepository extends JpaRepository<LongestStreak, Long> {
}
