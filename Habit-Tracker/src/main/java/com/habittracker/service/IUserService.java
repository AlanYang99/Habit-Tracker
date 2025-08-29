package com.habittracker.service;

import com.habittracker.dto.SimpleUserDto;
import com.habittracker.dto.UserDto;
import com.habittracker.dto.UserLoginRequestDto;
import com.habittracker.dto.UserRegistrationRequestDto;
import com.habittracker.entity.User;

public interface IUserService {
    public UserDto createUser(final UserRegistrationRequestDto userRegistrationRequestDto);
    public String loginUser(final UserLoginRequestDto userLoginRequestDto);
    public UserDto getUserSummary();
    public SimpleUserDto getSimpleUserSummary(final UserLoginRequestDto userLoginRequestDto);
}
