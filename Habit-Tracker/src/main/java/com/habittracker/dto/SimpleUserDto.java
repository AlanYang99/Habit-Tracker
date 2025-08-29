package com.habittracker.dto;

import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class SimpleUserDto {
    private String username;
    private String email;
    private String name;
}
