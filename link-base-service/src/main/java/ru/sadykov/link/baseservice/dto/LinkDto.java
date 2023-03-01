package ru.sadykov.link.baseservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class LinkDto {

    private String  shortLink;
    private String fullLink;
    private Integer visits;
}
