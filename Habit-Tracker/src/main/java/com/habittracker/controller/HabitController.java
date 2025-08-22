package com.habittracker.controller;

import com.habittracker.dto.HabitRequestDto;
import com.habittracker.dto.HabitResponseDto;
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
    private final Environment env;

    @PostMapping
    public ResponseEntity<?> createHabit(@Valid @RequestBody final HabitRequestDto habitRequestDto) {
        AbstractHabit habit;
        try {
            habit = habitService.createHabit(habitRequestDto);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body("Invalid habit data provided: " + e.getMessage());
        }

        HabitResponseDto habitResponseDto = new HabitResponseDto();
        BeanUtils.copyProperties(habitRequestDto, habitResponseDto);

        // Update response to consider days of week + calendar days

        return ResponseEntity.status(HttpStatus.CREATED).body(habitResponseDto);
    }

    // Put the logic of getting habits due for completion, here (habits can also be past and future, (add date query))
//    @GetMapping
//    public ResponseEntity<?> getDueHabits() {
//
//    };


    // Gets the habits belonging to the user
    // Consider a new get endpoint
    @GetMapping
    public ResponseEntity<?> getAllHabits(@RequestParam final Map<String, String> params) {
        List<AbstractHabit> habits;
        try {
            habits = habitService.getAllHabitsForCurrentUser(params);
        } catch (final Exception e) {
            return ResponseEntity.badRequest().body("Error retrieving habits: " + e.getMessage());
        }

        final List<HabitResponseDto> habitResponseDtos = habits.stream()
                .map(habit -> {
                    HabitResponseDto responseDto = new HabitResponseDto();
                    BeanUtils.copyProperties(habit, responseDto);
                    return responseDto;
                })
                .toList();

        return ResponseEntity.ok().body(habitResponseDtos);
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
