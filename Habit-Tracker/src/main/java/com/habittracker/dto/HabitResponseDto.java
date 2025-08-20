package com.habittracker.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.habittracker.enumtype.HabitFrequency;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
public class HabitResponseDto {
    private String name;
    private String description;
    private HabitFrequency frequency;
    private LocalDate startDate;
    private LocalDate endDate;
}
