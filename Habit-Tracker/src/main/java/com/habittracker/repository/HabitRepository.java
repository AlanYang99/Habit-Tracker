package com.habittracker.repository;

import com.habittracker.entity.AbstractHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<AbstractHabit, Long>, JpaSpecificationExecutor<AbstractHabit> {
    boolean existsByNameAndUserId(final String name, final Long userId);
    List<AbstractHabit> findByUserId(final Long userId);
    Optional<AbstractHabit> findByNameAndUserId(final String name, final Long userId);
    Optional<AbstractHabit> findByIdAndUserId(final Long id, final Long userId);
}
