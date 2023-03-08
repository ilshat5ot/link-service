package ru.sadykov.link.authenticationservice.exception;

public class UserAlreadyExistsException extends RuntimeException {
    private static final String MESSAGE = "user is exists!";

    public UserAlreadyExistsException() {
        super(MESSAGE);
    }
}
