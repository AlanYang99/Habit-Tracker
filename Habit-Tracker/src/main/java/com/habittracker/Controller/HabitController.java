package com.habittracker.Controller;

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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

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

//    @GetMapping
//    public ResponseEntity<?> getAllHabits() {
//
//    }
}
