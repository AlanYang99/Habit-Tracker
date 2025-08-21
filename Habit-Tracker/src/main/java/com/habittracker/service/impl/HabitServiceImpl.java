package com.habittracker.service.impl;

import com.habittracker.repository.*;
import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.*;
import com.habittracker.enumtype.HabitFrequency;
import com.habittracker.service.IGameStatService;
import com.habittracker.service.IHabitService;
import com.habittracker.service.IStreakService;
import com.habittracker.specification.CalendarHabitSpecification;
import com.habittracker.specification.DailyHabitSpecification;
import com.habittracker.specification.HabitSpecification;
import com.habittracker.specification.WeeklyHabitSpecification;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class HabitServiceImpl implements IHabitService {

    private final DailyHabitRepository dailyHabitRepository;
    private final CalendarHabitRepository calendarHabitRepository;
    private final WeeklyHabitRepository weeklyHabitRepository;
    private final HabitRepository habitRepository;
    private final HabitLogRepository habitLogRepository;
    private final IGameStatService gameStatService;
    private final IStreakService streakService;
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

    @Override
    public List<AbstractHabit> getAllHabitsForCurrentUser(final Map<String, String> params) {
        final User currentUser = userUtil.getCurrentUser();

        if (params.isEmpty()) {
            return habitRepository.findByUserId(currentUser.getId());
        }

        final LocalDate date = LocalDate.parse(params.get("date"));

        // At this point the only valid param is date, will update this piece of code once more valid query params exists
        return Stream.of(getAllValidDailyHabits(currentUser, date), getAllValidWeeklyHabits(currentUser, date),
                getAllValidCalendarHabits(currentUser, date)).flatMap(List::stream).collect(Collectors.toUnmodifiableList());
    }

    public List<DailyHabit> getAllValidDailyHabits(final User currentUser, final LocalDate date) {
        Specification<DailyHabit> spec = DailyHabitSpecification.userId(currentUser.getId());
        spec = spec.and(DailyHabitSpecification.validDate(date));

        return dailyHabitRepository.findAll(spec);
    }

    public List<WeeklyHabit> getAllValidWeeklyHabits(final User currentUser, final LocalDate date) {
        Specification<WeeklyHabit> spec = WeeklyHabitSpecification.userId(currentUser.getId());
        spec = spec.and(WeeklyHabitSpecification.validDate(date));

        return weeklyHabitRepository.findAll(spec);
    }

    public List<CalendarHabit> getAllValidCalendarHabits(final User currentUser, final LocalDate date) {
        Specification<CalendarHabit> spec = CalendarHabitSpecification.userId(currentUser.getId());
        spec = spec.and(CalendarHabitSpecification.validDate(date));

        return calendarHabitRepository.findAll(spec);
    }

    @Override
    public void completeHabit(final Long habitId) {
        final User currentUser = userUtil.getCurrentUser();
        final AbstractHabit habit = habitRepository.findByIdAndUserId(habitId, currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Habit not found or does not belong to the user."));

        if (habitLogRepository.existsByHabitIdAndDate(habitId, LocalDate.now())) {
            throw new IllegalArgumentException("Habit already completed for today.");
        }

        final HabitLog habitLog = new HabitLog();
        habitLog.setHabit(habit);
        habitLog.setUser(currentUser);
        habitLog.setCreationDate(LocalDate.now());
        habitLog.setCompleted(true);
        habitLogRepository.save(habitLog);

        gameStatService.updateGameStatsOnHabitCompletion();
        streakService.updateStreaks();
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