package ru.sadykov.link.authenticationservice.mapper;

import org.mapstruct.Mapper;
import ru.sadykov.link.authenticationservice.dto.AuthRequestDto;
import ru.sadykov.link.common.entity.User;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User authRequestToUser (AuthRequestDto authRequestDto);
}
