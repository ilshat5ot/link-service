package ru.sadykov.link.exception.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.sadykov.link.exception.ErrorMessage;
import ru.sadykov.link.exception.LinkNotFoundException;
import ru.sadykov.link.exception.WrongLinkException;

import static ru.sadykov.link.exception.Exceptions.SHORT_LINK_NOT_FOUND_MESSAGE;
import static ru.sadykov.link.exception.Exceptions.WRONG_LINK_MESSAGE;

/**Почитать разобраться!!!!*/
@Slf4j
@RestControllerAdvice
public class AppExceptionHandler {

    @ExceptionHandler(LinkNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handleLinkNotFoundException(LinkNotFoundException exception) {
        log.error(exception.getMessage());
        return exception.getMessage();
    }

    /**Можли перехватить экзепляр ошибки и вытащить мессадж и пробросить клиенту????
     * Почитать подумать*/
    @ExceptionHandler(WrongLinkException.class)
    //@ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ErrorMessage> handleWrongLinkException(WrongLinkException exception) {
        /**С стринг форматом не взлетела*/
        log.error("Вот такая ситуация: {} и немного такая {}",WRONG_LINK_MESSAGE, exception.getMessage());
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(new ErrorMessage(WRONG_LINK_MESSAGE, exception.getMessage()));
    }

}
