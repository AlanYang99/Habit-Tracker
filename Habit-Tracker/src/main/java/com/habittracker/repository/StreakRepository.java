package com.habittracker.repository;

import com.habittracker.entity.AbstractStreak;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

@NoRepositoryBean
public interface StreakRepository<T extends AbstractStreak> extends JpaRepository<T, Long> {
    T findByUserId(final Long userId);
}
