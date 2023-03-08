package ru.sadykov.link.authenticationservice.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;
import ru.sadykov.link.authenticationservice.service.RegistrationService;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SignupController {

    private final RegistrationService registrationService;

    @PostMapping("/signup")
    public void signup(@RequestBody AuthRequestDto authRequestDto) {
        registrationService.signUp(authRequestDto);
    }

}
