package com.habittracker.repository;

import com.habittracker.entity.DailyHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface DailyHabitRepository extends JpaRepository<DailyHabit, Long>, JpaSpecificationExecutor<DailyHabit> {
}