package ru.sadykov.link.baseservice.service;

import jakarta.servlet.http.HttpServletResponse;
import ru.sadykov.link.baseservice.dto.LinkDto;

import java.io.IOException;

public interface LinkService {

    String createShortLink(String fullLink);

    void redirect(String shortLink, HttpServletResponse response) throws IOException;

    void deleteLink(String shortLink);

    LinkDto getStatistics(String shortLink);
}
