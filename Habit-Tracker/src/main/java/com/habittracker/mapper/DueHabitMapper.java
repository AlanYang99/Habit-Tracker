package com.habittracker.mapper;

import com.habittracker.dto.DueHabitDto;
import com.habittracker.entity.AbstractHabit;
import org.springframework.stereotype.Component;

@Component
public class DueHabitMapper {

    public DueHabitDto mapToDto(final AbstractHabit habit, final boolean completionStatus) {
        return DueHabitDto.builder()
                .name(habit.getName())
                .description(habit.getDescription())
                .completionStatus(completionStatus)
                .build();
    }
}
