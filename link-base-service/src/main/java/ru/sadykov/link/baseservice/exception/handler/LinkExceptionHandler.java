package ru.sadykov.link.baseservice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sadykov.link.baseservice.exception.exeptions.ExceptionMessage;
import ru.sadykov.link.baseservice.exception.exeptions.LinkNotFoundException;
import ru.sadykov.link.baseservice.exception.exeptions.WrongLinkException;

import static ru.sadykov.link.baseservice.exception.exeptions.Exceptions.WRONG_LINK_MESSAGE;
import static ru.sadykov.link.baseservice.exception.exeptions.Exceptions.WRONG_URL_MESSAGE;

/**
 * Почитать разобраться!!!!
 */
@Slf4j
@RestControllerAdvice
public class LinkExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLinkNotFoundException(LinkNotFoundException exception) {
        log.error(exception.getMessage());
        return exception.getMessage();
    }

    @ExceptionHandler(WrongLinkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ExceptionMessage handleWrongLinkException(WrongLinkException exception) {
        if (WRONG_LINK_MESSAGE.equals(exception.getMessage())) {
            log.error("Ошибка: {}; Описание: {}", WRONG_LINK_MESSAGE, WRONG_URL_MESSAGE);
            return new ExceptionMessage(WRONG_LINK_MESSAGE, WRONG_URL_MESSAGE);
        } else {
            log.error("Ошибка: {}; Описание: {}", WRONG_LINK_MESSAGE, exception.getMessage());
            return new ExceptionMessage(WRONG_LINK_MESSAGE, exception.getMessage());
        }
    }
}
