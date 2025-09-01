package com.habittracker.controller;

import com.habittracker.service.IHabitLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/habit-log")
public class HabitLogController {

    private final IHabitLogService habitLogService;

    @GetMapping
    public ResponseEntity<?> getHabitLog() {
        return ResponseEntity.ok(habitLogService.getAllHabitLogs());
    }
}
