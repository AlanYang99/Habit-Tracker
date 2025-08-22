package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DueHabitDto {
    private String name;
    private String description;
    private boolean completionStatus;
}
