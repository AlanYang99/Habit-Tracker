package com.habittracker.service.impl;

import com.habittracker.Repository.HabitRepository;
import com.habittracker.Repository.UserRepository;
import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.*;
import com.habittracker.enumtype.HabitFrequency;
import com.habittracker.service.IHabitService;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements IHabitService {

    private final HabitRepository habitRepository;
    private final UserUtil userUtil;

    @Override
    public AbstractHabit createHabit(final HabitRequestDto habitRequestDto) {
        final User currentUser = userUtil.getCurrentUser();
        if (habitRepository.existsByNameAndUserId(habitRequestDto.getName(), currentUser.getId())) {
            throw new IllegalArgumentException("Habit with this name already exists for the user.");
        }

        final AbstractHabit habit;

        if (habitRequestDto.getFrequency() == HabitFrequency.WEEKLY) {
            habit = createWeeklyHabit(habitRequestDto);
        } else if (habitRequestDto.getFrequency() == HabitFrequency.CALENDAR) {
            habit = createCalendarHabit(habitRequestDto);
        } else {
            habit = createDailyHabit();
        }

        // Upgrade to factory pattern after
        BeanUtils.copyProperties(habitRequestDto, habit);
        habit.setUser(currentUser);
        habitRepository.save(habit);

        return habit;
    }

    public List<AbstractHabit> getAllHabitsForCurrentUser() {
        final User currentUser = userUtil.getCurrentUser();
        return habitRepository.findByUserId(currentUser.getId());
    }

    private AbstractHabit createDailyHabit() {
        return new DailyHabit();
    }

    private AbstractHabit createWeeklyHabit(final HabitRequestDto habitRequestDto) {
        final WeeklyHabit weeklyHabit = new WeeklyHabit();
        weeklyHabit.setDaysOfWeek(new HashSet<>(habitRequestDto.getDaysOfWeek()));
        return weeklyHabit;
    }

    private AbstractHabit createCalendarHabit(final HabitRequestDto habitRequestDto) {
        final CalendarHabit calendarHabit = new CalendarHabit();
        calendarHabit.setScheduledDates(new HashSet<>(habitRequestDto.getSpecificDates()));
        return calendarHabit;
    }

}
