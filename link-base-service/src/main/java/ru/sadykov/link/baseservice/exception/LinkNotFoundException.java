package ru.sadykov.link.baseservice.exception;

import static ru.sadykov.link.baseservice.exception.Exceptions.SHORT_LINK_NOT_FOUND_MESSAGE;

public class LinkNotFoundException extends RuntimeException {

    public LinkNotFoundException() {
        super(SHORT_LINK_NOT_FOUND_MESSAGE);
    }
}
