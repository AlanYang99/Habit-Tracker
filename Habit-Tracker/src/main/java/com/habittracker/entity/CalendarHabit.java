package com.habittracker.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "Calendar Habits")
public class CalendarHabit extends AbstractHabit {
    @ElementCollection
    @CollectionTable(
            name = "habit_scheduled_dates", // Name of the join table
            joinColumns = @JoinColumn(name = "habit_id") // Foreign key to the owning entity
    )
    @Column(name = "scheduled_date") // Name of the column storing the LocalDate
    private Set<LocalDate> scheduledDates;
}
