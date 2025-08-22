package com.habittracker.repository;

import com.habittracker.entity.GameStats;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface GameStatRepository extends JpaRepository<GameStats, Long> {
    GameStats findByUserId(final Long userId);
}
