package ru.sadykov.link.exception;

import static ru.sadykov.link.exception.Exceptions.WRONG_LINK_MESSAGE;

public class WrongLinkException extends RuntimeException{

    public WrongLinkException() {
        super(WRONG_LINK_MESSAGE);
    }

    public WrongLinkException(String message) {
        super(message);
    }
}
