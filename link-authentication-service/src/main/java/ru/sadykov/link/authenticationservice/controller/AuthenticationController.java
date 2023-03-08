package ru.sadykov.link.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;
import ru.sadykov.link.authenticationservice.service.AuthenticationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthRequestDto authRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getUserName(), authRequest.getPassword());
        return authenticationService.authenticate(usernamePasswordAuthenticationToken);
    }
}
