package com.habittracker.repository;

import com.habittracker.entity.WeeklyHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WeeklyHabitRepository extends JpaRepository<WeeklyHabit, Long>, JpaSpecificationExecutor<WeeklyHabit> {
}