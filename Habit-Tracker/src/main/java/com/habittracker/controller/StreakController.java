package com.habittracker.controller;

import com.habittracker.service.IStreakService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/streak")
public class StreakController {

    private IStreakService streakService;

    @GetMapping
    public ResponseEntity<?> getUserStreaks() {
        return ResponseEntity.ok(streakService.getUserStreaks());
    }
}
