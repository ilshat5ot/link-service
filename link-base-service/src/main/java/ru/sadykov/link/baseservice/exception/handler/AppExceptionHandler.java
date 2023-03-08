package ru.sadykov.link.baseservice.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sadykov.link.baseservice.exception.ExceptionMessage;
import ru.sadykov.link.baseservice.exception.exeptions.LinkNotFoundException;
import ru.sadykov.link.baseservice.exception.exeptions.WrongLinkException;

import static ru.sadykov.link.baseservice.exception.Exceptions.WRONG_LINK_MESSAGE;

/**
 * Почитать разобраться!!!!
 */
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    public ResponseEntity<String> handleLinkNotFoundException(LinkNotFoundException exception) {
        log.error(exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(exception.getMessage());
    }

    @ExceptionHandler(WrongLinkException.class)
    public ResponseEntity<ExceptionMessage> handleWrongLinkException(WrongLinkException exception) {
        /**С стринг форматом не взлетела*/
        log.error("Ошибка: {}; Описание: {}", WRONG_LINK_MESSAGE, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ExceptionMessage(WRONG_LINK_MESSAGE, exception.getMessage()));
    }
}
