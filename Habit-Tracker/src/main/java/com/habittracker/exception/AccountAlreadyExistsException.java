package com.habittracker.exception;

public class AccountAlreadyExistsException extends RuntimeException {
    public AccountAlreadyExistsException() {
        super("An account with this email and/or username already exists.");
    }

    public AccountAlreadyExistsException(final String message) {
        super(message);
    }
}
