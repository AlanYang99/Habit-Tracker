package com.habittracker.mapper;

import com.habittracker.dto.GameSummaryDto;
import com.habittracker.dto.SimpleUserDto;
import com.habittracker.dto.StreakSummaryDto;
import com.habittracker.dto.UserDto;
import com.habittracker.entity.Badge;
import com.habittracker.entity.GameStats;
import com.habittracker.entity.User;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class UserMapper {

    public SimpleUserDto mapToSimpleDto(final User user) {
       return SimpleUserDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).build();
    }

    public UserDto mapToDto(final User user, final Map<String, Long> streakInfo) {
        final GameStats gameStats = user.getGameStats();
        final int badges = user.getUserBadges().size();
        final GameSummaryDto gameSummaryDto = GameSummaryDto.builder().level(user.getGameStats().getUserLevel()).experience(user.getGameStats().getUserExp()).build();

        final StreakSummaryDto streakSummaryDto = StreakSummaryDto.builder().currentStreak(streakInfo.get("Current Streak").intValue()).longestStreak(streakInfo.get("Longest Streak").intValue()).build();
        return UserDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).gameSummary(gameSummaryDto).streakSummary(streakSummaryDto).badges(0).build();
    }

    public UserDto newUserDto(final User user) {
        final GameSummaryDto gameSummaryDto = GameSummaryDto.builder().level(1).experience(0).build();
        final StreakSummaryDto streakSummaryDto = StreakSummaryDto.builder().currentStreak(0).longestStreak(0).build();

        return UserDto.builder().username(user.getUsername()).name(user.getName()).email(user.getEmail()).gameSummary(gameSummaryDto).streakSummary(streakSummaryDto).badges(0).build();
    }
}
