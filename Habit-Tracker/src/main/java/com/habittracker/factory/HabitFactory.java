package com.habittracker.factory;

import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.AbstractHabit;
import com.habittracker.entity.CalendarHabit;
import com.habittracker.entity.DailyHabit;
import com.habittracker.entity.WeeklyHabit;
import org.springframework.beans.BeanUtils;

import java.time.LocalDate;
import java.util.HashSet;

public class HabitFactory {

    private HabitFactory () {

    }

    public static AbstractHabit createHabit(final HabitRequestDto habitRequestDto) {
        AbstractHabit habit;
        switch (habitRequestDto.getFrequency()) {
            case DAILY -> habit = createDailyHabit(habitRequestDto);
            case WEEKLY -> habit = createWeeklyHabit(habitRequestDto);
            case CALENDAR -> habit = createCalendarHabit(habitRequestDto);
            default -> throw new IllegalArgumentException("Unsupported frequency");
        }
        return habit;
    }

    private static DailyHabit createDailyHabit(final HabitRequestDto habitRequestDto) {
        final DailyHabit dailyHabit = new DailyHabit();
        BeanUtils.copyProperties(habitRequestDto, dailyHabit);
        return dailyHabit;
    }

    private static WeeklyHabit createWeeklyHabit(final HabitRequestDto habitRequestDto) {
        final WeeklyHabit weeklyHabit = new WeeklyHabit();
        BeanUtils.copyProperties(habitRequestDto, weeklyHabit);
        weeklyHabit.setDaysOfWeek(new HashSet<>(habitRequestDto.getDaysOfWeek()));
        return weeklyHabit;
    }

    private static CalendarHabit createCalendarHabit(final HabitRequestDto habitRequestDto) {
        final CalendarHabit calendarHabit = new CalendarHabit();
        BeanUtils.copyProperties(habitRequestDto, calendarHabit);
        calendarHabit.setScheduledDates(new HashSet<>(habitRequestDto.getSpecificDates()));
        calendarHabit.setStartDate(LocalDate.now());
        calendarHabit.setEndDate(LocalDate.now());
        return calendarHabit;
    }
}
