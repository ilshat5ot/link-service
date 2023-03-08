package ru.sadykov.link.authenticationservice.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;
import ru.sadykov.link.authenticationservice.service.AuthenticationService;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;
    private final JWTService jwtService;

    private final CustomUserDetailsServiceImpl customUserDetailsService;

    @Override
    public String authenticate(UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) {
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);
        return jwtService.createToken(customUserDetailsService
                .loadUserByUsername(usernamePasswordAuthenticationToken.getName()));
    }
}
