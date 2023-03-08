package ru.sadykov.link.baseservice.controller;

import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public String getShortLink(@RequestBody String fullLink) {
        return linkService.createShortLink(fullLink);
    }

    @GetMapping("/{shortLink}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    public void redirectForFullLink(@PathVariable String shortLink, HttpServletResponse response) throws IOException {
        linkService.redirect(shortLink, response);
    }

    @DeleteMapping("/{shortLink}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public void deleteLink(@PathVariable String shortLink) {
        linkService.deleteLink(shortLink);
    }

    @GetMapping("/statistic/{shortLink}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public LinkDto getLinkStatistics(@PathVariable String shortLink) {
        return linkService.getStatistics(shortLink);
    }

}
