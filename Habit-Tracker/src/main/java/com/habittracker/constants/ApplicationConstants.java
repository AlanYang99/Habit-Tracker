package com.habittracker.constants;

public class ApplicationConstants {

    private ApplicationConstants() {
        // Prevent Instantiation
    }

    public static final String JWT_SECRET_KEY = "JWT_SECRET_KEY";
    public static final String JWT_SECRET_VALUE = "f90f525180d4e84a6esecret091457hb1i2841ydhq98key8fas821";
    public static final String JWT_HEADER = "Authorization";

    public static final Long JWT_EXPIRATION_TIME = 86400000L; // 1 day in milliseconds
}
