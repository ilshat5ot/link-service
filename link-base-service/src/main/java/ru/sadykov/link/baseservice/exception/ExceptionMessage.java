package ru.sadykov.link.baseservice.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionMessage {

    private String generalMessage;
    private String detailedMessage;
}
