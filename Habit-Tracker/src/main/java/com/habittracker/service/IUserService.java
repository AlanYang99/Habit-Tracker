package com.habittracker.service;

import com.habittracker.dto.UserLoginRequestDto;
import com.habittracker.dto.UserRegistrationRequestDto;
import com.habittracker.entity.User;

public interface IUserService {
    public void createUser(final UserRegistrationRequestDto userRegistrationRequestDto);
    public String loginUser(final UserLoginRequestDto userLoginRequestDto);
    public User getUser(final String username);
}
