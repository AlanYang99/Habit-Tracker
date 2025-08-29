package com.habittracker.service.impl;

import com.habittracker.entity.GameStats;
import com.habittracker.repository.GameStatRepository;
import com.habittracker.entity.User;
import com.habittracker.service.IGameStatService;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Service;

import static com.habittracker.constants.ApplicationConstants.EXPERIENCE_LEVEL_MAP;

@Service
@RequiredArgsConstructor
public class GameStatServiceImpl implements IGameStatService {

    private final GameStatRepository gameStatRepository;
    private final UserUtil userUtil;

    @Override
    public void updateGameStatsOnHabitCompletion() {
        final User currentUser = userUtil.getCurrentUser();
        final int currentExp = currentUser.getGameStats().getUserExp();
        if (currentExp + 10 >= EXPERIENCE_LEVEL_MAP.get(currentUser.getGameStats().getUserLevel())) {
            currentUser.getGameStats().setUserLevel(currentUser.getGameStats().getUserLevel() + 1);
            currentUser.getGameStats().setUserExp(currentUser.getGameStats().getUserExp() + 10 - EXPERIENCE_LEVEL_MAP.get(currentUser.getGameStats().getUserLevel() - 1));
        } else {
            currentUser.getGameStats().setUserExp(currentExp + 10);
        }

        gameStatRepository.save(currentUser.getGameStats());
    }

    @Override
    public GameStats getGameStatForCurrentUser() {
        final User currentUser = userUtil.getCurrentUser();

        GameStats stats = currentUser.getGameStats();
        if (stats != null && Hibernate.isInitialized(stats)) {
            return stats;
        }

        return gameStatRepository.findByUserId(currentUser.getId());
    }
}
