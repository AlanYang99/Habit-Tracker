package com.habittracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.habittracker.enumtype.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;

@Data
public class HabitRequestDto {

    @NotBlank(message = "Habit Name is required")
    @Size(max = 20, message = "Name must be at most 20 characters")
    private String name;

    @NotBlank(message = "Habit Description is required")
    @Size(max = 100, message = "Name must be at most 20 characters")
    private String description;

    @NotNull(message = "Frequency is required")
    private HabitFrequency frequency;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate endDate;

    private List<DayOfWeek> daysOfWeek;

    private List<LocalDate> specificDates;
}
