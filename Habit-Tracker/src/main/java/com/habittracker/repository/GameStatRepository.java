package com.habittracker.repository;

import com.habittracker.entity.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GameStatRepository extends JpaRepository<GameStats, Long> {
}
