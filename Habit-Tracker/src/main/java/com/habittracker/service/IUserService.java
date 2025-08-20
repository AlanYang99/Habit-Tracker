package com.habittracker.service;

import com.habittracker.dto.UserRegistrationRequestDto;

public interface IUserService {
    public void createUser(final UserRegistrationRequestDto userRegistrationRequestDto);
}
