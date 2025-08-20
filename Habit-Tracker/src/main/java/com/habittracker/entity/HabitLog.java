package com.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "Habit Logs")
public class HabitLog extends BaseEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "habit_id", nullable = false)
    private AbstractHabit habit;

    @Column(name = "creation_date", nullable = false)
    private LocalDate creationDate;

    @Column(name = "completed", nullable = false)
    private boolean completed;
}
