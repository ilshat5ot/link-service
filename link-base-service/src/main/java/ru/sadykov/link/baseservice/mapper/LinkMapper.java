package ru.sadykov.link.baseservice.mapper;

import org.mapstruct.Mapper;
import ru.sadykov.link.baseservice.dto.LinkDto;
import ru.sadykov.link.common.entity.Link;

@Mapper(componentModel = "spring")
public interface LinkMapper {

    LinkDto linkToLinkDto(Link link);
}
