package com.habittracker.controller;

import com.habittracker.service.IGameStatService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/game-stat")
public class GameStatController {

    private final IGameStatService gameStatService;

    @GetMapping
    public ResponseEntity<?> getGameStat() {
        return ResponseEntity.ok(gameStatService.getGameStatForCurrentUser());
    }
}