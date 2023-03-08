package ru.sadykov.link.authenticationservice.service;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;

public interface AuthenticationService {
    String authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken);
}
