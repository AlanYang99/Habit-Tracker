package com.habittracker.Repository;

import com.habittracker.entity.HabitLog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitLogRepository extends JpaRepository<HabitLog, Long> {
}
