package ru.sadykov.link.authenticationservice.service;

import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;

public interface RegistrationService {
    void signUp(AuthRequestDto authRequestDto);
}
