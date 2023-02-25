package ru.sadykov.link.mapper;

import org.mapstruct.Mapper;
import ru.sadykov.link.dto.LinkDto;
import ru.sadykov.link.model.Link;

@Mapper (componentModel = "spring")
public interface LinkMapper {

    LinkDto linkToLinkDto(Link link);
}
