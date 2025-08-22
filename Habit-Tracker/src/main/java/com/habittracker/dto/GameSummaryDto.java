package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class GameSummaryDto {
    private int level;
    private int experience;
}
