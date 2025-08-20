package com.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Weekly Habits")
public class WeeklyHabit extends AbstractHabit {

    @ElementCollection(targetClass = DayOfWeek.class)
    @CollectionTable(
            name = "habit_scheduled_days", // Name of the join table
            joinColumns = @JoinColumn(name = "habit_id") // Foreign key to the owning entity
    )
    @Column(name = "scheduled_day") // Name of the column storing the LocalDate
    private Set<DayOfWeek> daysOfWeek;
}
