package com.habittracker.constants;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ApplicationConstants {

    private ApplicationConstants() {
        // Prevent Instantiation
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET_KEY";
    public static final String JWT_SECRET_VALUE = "f90f525180d4e84a6esecret091457hb1i2841ydhq98key8fas821";
    public static final String JWT_HEADER = "Authorization";

    public static final Long JWT_EXPIRATION_TIME = 86400000L; // 1 day in milliseconds

    public static final Map<Integer, Integer> EXPERIENCE_LEVEL_MAP = new HashMap<>();
    static {
        EXPERIENCE_LEVEL_MAP.put(1, 50);
        EXPERIENCE_LEVEL_MAP.put(2, 80);
        EXPERIENCE_LEVEL_MAP.put(3, 120);
        EXPERIENCE_LEVEL_MAP.put(4, 180);
        EXPERIENCE_LEVEL_MAP.put(5, 250);
        EXPERIENCE_LEVEL_MAP.put(6, 350);
        EXPERIENCE_LEVEL_MAP.put(7, 500);
        EXPERIENCE_LEVEL_MAP.put(8, 660);
        EXPERIENCE_LEVEL_MAP.put(9, 850);
    }
}