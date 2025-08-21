package com.habittracker.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

    private ApplicationConstants() {
        // Prevent Instantiation
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET_KEY";
    public static final String JWT_SECRET_VALUE = "f90f525180d4e84a6esecret091457hb1i2841ydhq98key8fas821";
    public static final String JWT_HEADER = "Authorization";

    public static final Long JWT_EXPIRATION_TIME = 86400000L; // 1 day in milliseconds

    public static final Map<Integer, Integer> EXPERIENCE_LEVEL_MAP = new HashMap<>();
    static {
        experienceLevelMap.put(1, 50);
        experienceLevelMap.put(2, 80);
        experienceLevelMap.put(3, 120);
        experienceLevelMap.put(4, 180);
        experienceLevelMap.put(5, 250);
        experienceLevelMap.put(6, 350);
        experienceLevelMap.put(7, 500);
        experienceLevelMap.put(8, 660);
        experienceLevelMap.put(9, 850);
    }
}

/*
@Service
public class HabitService {

    @Autowired
    private HabitRepository habitRepository;

    @Autowired
    private HabitRecordRepository habitRecordRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RewardService rewardService;

    @Transactional
    public HabitCompletionResponse completeHabit(Long habitId, HabitCompletionRequest request) {
        // Step 1: Get the habit and user
        Habit habit = habitRepository.findById(habitId).orElseThrow(() -> new NotFoundException("Habit not found"));
        User user = habit.getUser();  // assuming habit has a 'user' field

        // Step 2: Check if the habit has already been completed today
        LocalDate today = LocalDate.now();
        HabitRecord existingRecord = habitRecordRepository.findByHabitAndDate(habit, today);

        if (existingRecord != null) {
            throw new IllegalStateException("Habit has already been completed today.");
        }

        // Step 3: Create a new HabitRecord for today's completion
        HabitRecord habitRecord = new HabitRecord();
        habitRecord.setHabit(habit);
        habitRecord.setUser(user);
        habitRecord.setDate(today);
        habitRecord.setStatus(request.getStatus());
        habitRecord.setNotes(request.getNotes());
        habitRecord.setPoints(request.getPoints());

        habitRecordRepository.save(habitRecord);

        // Step 4: Update the user’s streak if completed successfully
        if ("SUCCESS".equals(request.getStatus())) {
            updateStreak(user, habit);
        }

        // Step 5: Update the user’s total points
        user.setTotalPoints(user.getTotalPoints() + request.getPoints());
        userRepository.save(user);

        // Step 6: Trigger any rewards if the user has enough points
        rewardService.checkAndGiveReward(user);

        // Step 7: Check if user leveled up
        updateUserLevel(user);

        // Step 8: Return the response with updated data
        HabitCompletionResponse response = new HabitCompletionResponse();
        response.setMessage("Habit completed successfully!");
        response.setPoints(user.getTotalPoints());
        response.setStreak(user.getStreakCount());

        return response;
    }

    private void updateStreak(User user, Habit habit) {
        // Update streak logic
        Streak streak = streakRepository.findByUserAndHabit(user, habit);
        if (streak == null) {
            streak = new Streak();
            streak.setUser(user);
            streak.setHabit(habit);
            streak.setStreakCount(1);  // First completion
        } else {
            streak.setStreakCount(streak.getStreakCount() + 1);
        }
        streakRepository.save(streak);
    }

    private void updateUserLevel(User user) {
        // Logic to level up based on total points or streaks
        int level = calculateLevel(user.getTotalPoints());
        user.setLevel(level);
        userRepository.save(user);
    }

    private int calculateLevel(int totalPoints) {
        // For example, level up every 1000 points
        return totalPoints / 1000;
    }
}

 */