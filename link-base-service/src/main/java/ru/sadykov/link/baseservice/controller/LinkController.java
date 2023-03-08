package ru.sadykov.link.baseservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import ru.sadykov.link.baseservice.dto.LinkDto;
import ru.sadykov.link.baseservice.service.LinkService;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class LinkController {

    private final LinkService linkService;

    @PostMapping("/short-link")
    public String getShortLink(@RequestBody String fullLink) {
        return linkService.createShortLink(fullLink);
    }

    @GetMapping("/{shortLink}")
    public void redirectForFullLink(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        linkService.redirect(shortLink, response);
    }

    @DeleteMapping("/{shortLink}")
    public void deleteLink(@PathVariable String shortLink) {
        linkService.deleteLink(shortLink);
    }

    @GetMapping("/statistic/{shortLink}")
    public LinkDto getLinkStatistics(@PathVariable String shortLink) {
        return linkService.getStatistics(shortLink);
    }

}
