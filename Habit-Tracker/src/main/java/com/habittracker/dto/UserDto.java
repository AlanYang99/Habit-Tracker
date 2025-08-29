package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@EqualsAndHashCode(callSuper = true)
public class UserDto extends SimpleUserDto {
    private GameSummaryDto gameSummary;
    private StreakSummaryDto streakSummary;
    private int badges;
}
