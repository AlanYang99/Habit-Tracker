package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.Set;

@Data
@Builder
public class HabitDto {
    private String name;
    private String description;
    private LocalDate startDate;
    private LocalDate endDate;
    private Set<DayOfWeek> daysOfWeek;
    private Set<LocalDate> scheduledDates;
}
