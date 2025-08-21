package com.habittracker.repository;

import com.habittracker.entity.CalendarHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CalendarHabitRepository extends JpaRepository<CalendarHabit, Long>, JpaSpecificationExecutor<CalendarHabit> {
}
