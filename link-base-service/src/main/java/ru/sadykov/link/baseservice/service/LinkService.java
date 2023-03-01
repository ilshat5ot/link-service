package ru.sadykov.link.baseservice.service;

import ru.sadykov.link.baseservice.dto.LinkDto;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface LinkService {
    String createShortLink(String fullLink);

    void redirect(String shortLink, HttpServletResponse response) throws IOException;

    void deleteLink(String shortLink);

    LinkDto getStatistics(String shortLink);
}
