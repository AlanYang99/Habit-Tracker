package com.habittracker.Repository;

import com.habittracker.entity.AbstractHabit;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<AbstractHabit, Long> {
    boolean existsByNameAndUserId(String name, Long userId);
    List<AbstractHabit> findByUserId(Long userId);
    Optional<AbstractHabit> findByNameAndUserId(String name, Long userId);
}
