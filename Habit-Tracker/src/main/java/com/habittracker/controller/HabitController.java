package com.habittracker.controller;

import com.habittracker.dto.DueHabitDto;
import com.habittracker.dto.HabitDto;
import com.habittracker.dto.HabitRequestDto;
import com.habittracker.entity.AbstractHabit;
import com.habittracker.service.IHabitService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {

    private final IHabitService habitService;

    @PostMapping
    public ResponseEntity<?> createHabit(@RequestBody final HabitRequestDto habitRequestDto) {
        try {
            final HabitDto habitDto = habitService.createHabit(habitRequestDto);
            return ResponseEntity.status(HttpStatus.CREATED).body(habitDto);

        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid habit data provided: " + e.getMessage());
        }
    }

    // Put the logic of getting habits due for completion, here (habits can also be past and future, (add date query))
    @GetMapping("/due")
    public ResponseEntity<?> getDueHabits(@RequestParam final Map<String, String> params) {
        try {
            final List<DueHabitDto> dueHabits = habitService.getDueHabits(params);
            return ResponseEntity.ok(dueHabits);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving habits: " + e.getMessage());
        }
    }

    // Gets the habits belonging to the user
    // Consider a new get endpoint
    @GetMapping
    public ResponseEntity<?> getAllHabits(@RequestParam final Map<String, String> params) {
        try {
            final List<HabitDto> habits = habitService.getAllHabitsForCurrentUser(params);
            return ResponseEntity.ok().body(habits);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving habits: " + e.getMessage());
        }
    }

    @PostMapping("/{habitId}/complete")
    public ResponseEntity<?> completeHabit(@PathVariable final Long habitId) {
        try {
            habitService.completeHabit(habitId);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body("Couldn't complete habit : " + e.getMessage());
        }
        return ResponseEntity.ok().body("Habit has been completed");
    }
}
