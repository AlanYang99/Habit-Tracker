package com.habittracker.Controller;

import com.habittracker.service.IHabitService;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/habit")
@RequiredArgsConstructor
public class HabitController {

    private final IHabitService habitService;
    private final Environment env;

    @PostMapping
    public ResponseEntity<?> createHabit() {
        habitService.createHabit("Morning Exercise", "Daily morning exercise routine");
        return ResponseEntity.ok(null);
    }
}
