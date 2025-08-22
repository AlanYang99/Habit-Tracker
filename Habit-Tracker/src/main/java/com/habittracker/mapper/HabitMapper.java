package com.habittracker.mapper;

import com.habittracker.dto.HabitDto;
import com.habittracker.entity.CalendarHabit;
import com.habittracker.entity.DailyHabit;
import com.habittracker.entity.WeeklyHabit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {
    public HabitDto mapToDto(final DailyHabit habit) {
        return HabitDto.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .build();
    }

    public HabitDto mapToDto(final WeeklyHabit habit) {
        return HabitDto.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .daysOfWeek(habit.getDaysOfWeek())
                .build();
    }

    public HabitDto mapToDto(final CalendarHabit habit) {
        return HabitDto.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate())
                .scheduledDates(habit.getScheduledDates())
                .build();
    }
}
