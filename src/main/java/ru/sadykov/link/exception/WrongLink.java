package ru.sadykov.link.exception;

public class WrongLink extends RuntimeException{

    private static final String MESSAGE = "Ссылка не надена!";

    public WrongLink() {
        super(MESSAGE);
    }
}
