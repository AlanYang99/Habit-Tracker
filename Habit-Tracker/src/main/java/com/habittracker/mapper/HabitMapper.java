package com.habittracker.mapper;

import com.habittracker.dto.HabitDto;
import com.habittracker.entity.AbstractHabit;
import com.habittracker.entity.CalendarHabit;
import com.habittracker.entity.WeeklyHabit;
import org.springframework.stereotype.Component;

@Component
public class HabitMapper {
    public HabitDto mapToDto(final AbstractHabit habit) {
        HabitDto.HabitDtoBuilder builder = HabitDto.builder()
                .id(habit.getId())
                .name(habit.getName())
                .description(habit.getDescription())
                .startDate(habit.getStartDate())
                .endDate(habit.getEndDate());

        if (habit instanceof WeeklyHabit weekly) {
            builder.daysOfWeek(weekly.getDaysOfWeek());
        } else if (habit instanceof CalendarHabit calendar) {
            builder.scheduledDates(calendar.getScheduledDates());
        }

        return builder.build();
    }
}
