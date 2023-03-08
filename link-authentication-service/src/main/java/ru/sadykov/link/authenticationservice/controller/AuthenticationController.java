package ru.sadykov.link.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;
import ru.sadykov.link.authenticationservice.service.AuthenticationService;

@RestController
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/authenticate")
    public String createAuthenticationToken(@RequestBody AuthRequestDto authRequest) {
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                new UsernamePasswordAuthenticationToken(authRequest.getName(), authRequest.getPassword());
        return authenticationService.authenticate(usernamePasswordAuthenticationToken);
    }
}
