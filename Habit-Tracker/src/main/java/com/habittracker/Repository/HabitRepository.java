package com.habittracker.Repository;

import com.habittracker.entity.AbstractHabit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HabitRepository extends JpaRepository<AbstractHabit, Long> {
}
