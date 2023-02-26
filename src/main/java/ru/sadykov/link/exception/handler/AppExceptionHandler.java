package ru.sadykov.link.exception.handler;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sadykov.link.exception.LinkNotFoundException;
import ru.sadykov.link.exception.WrongLinkException;

import static ru.sadykov.link.exception.Exceptions.SHORT_LINK_NOT_FOUND_MESSAGE;
import static ru.sadykov.link.exception.Exceptions.WRONG_LINK_MESSAGE;

/**Почитать разобраться!!!!*/
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLinkNotFoundException() {
        return SHORT_LINK_NOT_FOUND_MESSAGE;
    }

    /**Можли перехватить экзепляр ошибки и вытащить мессадж и пробросить клиенту????
     * Почитать подумать*/
    @ExceptionHandler(WrongLinkException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String handleWrongLinkException() {
        return WRONG_LINK_MESSAGE;
    }

}
