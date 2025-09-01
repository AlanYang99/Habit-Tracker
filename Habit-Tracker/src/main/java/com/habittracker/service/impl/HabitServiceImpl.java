package com.habittracker.service.impl;

import com.habittracker.dto.DueHabitDto;
import com.habittracker.dto.HabitDto;
import com.habittracker.factory.HabitFactory;
import com.habittracker.mapper.DueHabitMapper;
import com.habittracker.mapper.HabitMapper;
import com.habittracker.repository.*;
import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.*;
import com.habittracker.service.IGameStatService;
import com.habittracker.service.IHabitService;
import com.habittracker.service.IStreakService;
import com.habittracker.specification.CalendarHabitSpecification;
import com.habittracker.specification.DailyHabitSpecification;
import com.habittracker.specification.WeeklyHabitSpecification;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.cglib.core.Local;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    private final HabitMapper habitMapper;
    private final DueHabitMapper dueHabitMapper;
    private final UserRepository userRepository;

    @Override
    public HabitDto createHabit(final HabitRequestDto habitRequestDto) {
        final User currentUser = userUtil.getCurrentUser();
        if (habitRepository.existsByNameAndUserId(habitRequestDto.getName(), currentUser.getId())) {
            throw new IllegalArgumentException("Habit with this name already exists for the user.");
        }

        final AbstractHabit habit = HabitFactory.createHabit(habitRequestDto);
        habit.setUser(currentUser);
        habitRepository.save(habit);
        return habitMapper.mapToDto(habit);
    }

    @Override
    public List<HabitDto> getAllHabitsForCurrentUser(final Map<String, String> params) {
        return habitRepository.findByUserId(userUtil.getCurrentUser().getId()).stream().map(habitMapper::mapToDto).toList();
    }

    @Override
    public List<DueHabitDto> getDueHabits(final Map<String, String> params) {
        final User currentUser = userUtil.getCurrentUser();

        final LocalDate today = LocalDate.now();
        final LocalDate date = params.containsKey("date") ? LocalDate.parse(params.get("date")) : today;
        final List<AbstractHabit> habits = getDueHabitsByDate(date);

        final Map<Long, HabitLog> habitLogsMap = habitLogRepository.findByUserIdAndCreationDate(currentUser.getId(), date).stream()
                .collect(Collectors.toMap(log -> log.getHabit().getId(), Function.identity()));

        List<DueHabitDto> dueHabits;
        if (date.isEqual(today)) {
            dueHabits = habits.stream().map(habit -> (dueHabitMapper.mapToDto(habit, habitLogsMap.containsKey(habit.getId())))).toList();
        } else if (date.isAfter(today)) {
            dueHabits = habits.stream().map(habit -> (dueHabitMapper.mapToDto(habit, false))).toList();
        } else {
            dueHabits = habits.stream().map(habit -> (
               dueHabitMapper.mapToDto(habit, habitLogsMap.get(habit.getId()).isCompleted()))).toList();
        }

        return dueHabits;
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
    @Transactional
    public void completeHabit(final Long habitId) {
        final User currentUser = userUtil.getCurrentUser();
        final AbstractHabit habit = habitRepository.findByIdAndUserId(habitId, currentUser.getId())
                .orElseThrow(() -> new IllegalArgumentException("Habit not found or does not belong to the user."));

        if (habitLogRepository.existsByHabitIdAndCreationDate(habitId, LocalDate.now())) {
            throw new IllegalArgumentException("Habit already completed for today.");
        }

        final HabitLog habitLog = new HabitLog();
        habitLog.setHabit(habit);
        habitLog.setUser(currentUser);
        habitLog.setCreationDate(LocalDate.now());
        habitLog.setCompleted(true);
        habitLog.setNotes(String.format("Completed %s", habit.getName()));
        habitLogRepository.save(habitLog);

        gameStatService.updateGameStatsOnHabitCompletion();
        streakService.updateStreaks();
    }

    public void markIncompleteHabits() {
        final List<User> users = userRepository.findAll();
        final LocalDate yesterDay = LocalDate.now().minusDays(1);

        users.forEach(user -> {
            final List<AbstractHabit> habits = habitRepository.findUnloggedHabitsByDate(user.getId(), yesterDay);

            habits.forEach( habit -> {
                final HabitLog habitLog = new HabitLog();
                habitLog.setHabit(habit);
                habitLog.setUser(user);
                habitLog.setCreationDate(yesterDay);
                habitLog.setCompleted(false);
                habitLog.setNotes(String.format("Failed to complete %s", habit.getName()));
                habitLogRepository.save(habitLog);
            });
        });
    }

    private List<AbstractHabit> getDueHabitsByDate(final LocalDate date) {
        final User currentUser = userUtil.getCurrentUser();

        return Stream.of(
                findHabitsBySpec(DailyHabitSpecification.userId(currentUser.getId())
                        .and(DailyHabitSpecification.validDate(date)), dailyHabitRepository),
                findHabitsBySpec(WeeklyHabitSpecification.userId(currentUser.getId())
                        .and(WeeklyHabitSpecification.validDate(date)), weeklyHabitRepository),
                findHabitsBySpec(CalendarHabitSpecification.userId(currentUser.getId())
                        .and(CalendarHabitSpecification.validDate(date)), calendarHabitRepository))
                        .flatMap(List::stream).collect(Collectors.toUnmodifiableList());
    }

    private <T extends AbstractHabit> List<T> findHabitsBySpec(final Specification<T> spec, org.springframework.data.jpa.repository.JpaSpecificationExecutor<T> repo) {
        return repo.findAll(spec);
    }
}