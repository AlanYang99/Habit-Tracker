package com.habittracker.dto;

public record UserLoginResponseDto(String message, String token, SimpleUserDto user) {}
