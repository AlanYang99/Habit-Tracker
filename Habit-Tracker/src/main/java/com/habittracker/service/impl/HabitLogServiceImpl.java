package com.habittracker.service.impl;

import com.habittracker.dto.HabitDto;
import com.habittracker.dto.HabitLogDto;
import com.habittracker.entity.HabitLog;
import com.habittracker.entity.User;
import com.habittracker.repository.HabitLogRepository;
import com.habittracker.service.IHabitLogService;
import com.habittracker.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitLogServiceImpl implements IHabitLogService {

    private final HabitLogRepository habitLogRepository;
    private final UserUtil userUtil;

    @Override
    public List<HabitLogDto> getAllHabitLogs() {
        final User currentUser = userUtil.getCurrentUser();
        return habitLogRepository.findByUserId(
                currentUser.getId(),
                Sort.by(Sort.Direction.DESC, "createdAt")
        ).stream().map(habitLog -> {
            final HabitLogDto habitLogDto = new HabitLogDto();
            BeanUtils.copyProperties(habitLog, habitLogDto);
            return habitLogDto;
        }).toList();
    }
}