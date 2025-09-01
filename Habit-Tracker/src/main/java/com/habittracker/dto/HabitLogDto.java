package com.habittracker.dto;

import lombok.Data;
import java.time.LocalDate;

@Data
public class HabitLogDto {
    private long id;
    private LocalDate creationDate;
    private String notes;
}