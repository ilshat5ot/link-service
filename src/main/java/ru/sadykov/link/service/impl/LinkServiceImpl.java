package ru.sadykov.link.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.sadykov.link.dto.LinkDto;
import ru.sadykov.link.exception.LinkNotFoundException;
import ru.sadykov.link.exception.WrongLinkException;
import ru.sadykov.link.mapper.LinkMapper;
import ru.sadykov.link.model.Link;
import ru.sadykov.link.repository.LinkRepository;
import ru.sadykov.link.service.LinkService;
import ru.sadykov.link.service.ShortLinkCreatorService;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Slf4j
public class
LinkServiceImpl implements LinkService{

    private final LinkRepository linkRepository;
    private final ShortLinkCreatorService shortLinkService;
    private final LinkMapper linkMapper;

    /**В постмане вылетает внутрення ошибка сервера пользователь не знает что конкретно не так?*/
    @Override
    public String createShortLink(String fullLink) {
        checkLink(fullLink);
        String shortLink = generateShortLink(fullLink);
        if (!linkRepository.existsById(shortLink)) {
            linkRepository.save(Link.builder()
                    .fullLink(fullLink)
                    .shortLink(shortLink)
                    .visits(0)
                    .build());
        }
        return shortLink;
    }

    /**Хорошая ли практик пробрасывать исключение в контроллер?*/
    @Override
    @Transactional
    public void redirect(String shortLink, HttpServletResponse response) throws IOException {
        Link link = linkRepository.findById(shortLink).orElseThrow(LinkNotFoundException::new);
        Integer visits = link.getVisits() + 1;
        linkRepository.updateVisit(visits, shortLink);
        String fullLink = link.getFullLink();
        response.sendRedirect(fullLink);
    }

    @Override
    public void deleteLink(String shortLink) {
        linkRepository.findById(shortLink).orElseThrow(WrongLinkException::new);
        linkRepository.deleteById(shortLink);
    }

    /**В постмане вылетает статус 500 без объяснений ошибки. Скорее всего это не правильно*/
    @Override
    public LinkDto getStatistics(String shortLink) {
        return linkMapper.linkToLinkDto(linkRepository.findById(shortLink)
                .orElseThrow(WrongLinkException::new));
    }

    private String generateShortLink(String fullLink) {
        return shortLinkService.convertFullLinkToShort(fullLink);
    }

    /**Подумать разобраться*/
    private void checkLink(String fullLink) {
        RestTemplate restTemplate = new RestTemplate();
        try{
            restTemplate.exchange(fullLink, HttpMethod.GET, null, String.class);
            System.out.println();
        } catch (RuntimeException e) {
            /**Теперь этот код можно вынести в эксепшен хендлер*/
            //log.error("ссылка {} не работает!", fullLink);
            throw new WrongLinkException(e.getMessage());
        }
    }
}
