package com.habittracker.repository;

import com.habittracker.entity.CurrentStreak;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CurrentStreakRepository extends StreakRepository<CurrentStreak> {
}
