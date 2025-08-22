package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class StreakSummaryDto {
    private int currentStreak;
    private int longestStreak;
}
