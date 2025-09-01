package com.habittracker.repository;

import com.habittracker.entity.AbstractHabit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface HabitRepository extends JpaRepository<AbstractHabit, Long>, JpaSpecificationExecutor<AbstractHabit> {
    boolean existsByNameAndUserId(final String name, final Long userId);
    List<AbstractHabit> findByUserId(final Long userId);
    Optional<AbstractHabit> findByNameAndUserId(final String name, final Long userId);
    Optional<AbstractHabit> findByIdAndUserId(final Long id, final Long userId);

    @Query("""
    SELECT h FROM AbstractHabit h
    WHERE h.dueDate = :day
      AND h.user.id = :userId
      AND NOT EXISTS (
          SELECT 1 FROM HabitLog log
          WHERE log.habit.id = h.id
            AND log.creationDate = :yesterDay
            AND log.user.id = :userId
      )""")
    List<AbstractHabit> findUnloggedHabitsByDate(@Param("userId") Long userId, @Param("day") LocalDate day);
}
