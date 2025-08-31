package com.habittracker.factory;

import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.AbstractHabit;
import com.habittracker.entity.DailyHabit;
import org.springframework.beans.BeanUtils;

public class HabitFactory {

    public static AbstractHabit createHabit(final HabitRequestDto habitRequestDto) {
        AbstractHabit habit;
        switch (habitRequestDto.getFrequency()) {
            case DAILY -> habit = createDailyHabit(habitRequestDto);
            default -> throw new IllegalArgumentException("Unsupported frequency");
        }
        return habit;
    }

    private static DailyHabit createDailyHabit(final HabitRequestDto habitRequestDto) {
        final DailyHabit dailyHabit = new DailyHabit();
        BeanUtils.copyProperties(habitRequestDto, dailyHabit);
        return dailyHabit;
    }
}
