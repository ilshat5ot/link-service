package ru.sadykov.link.service.impl;

import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ActiveProfiles;
import ru.sadykov.link.exception.WrongLinkException;
import ru.sadykov.link.repository.LinkRepository;
import ru.sadykov.link.service.LinkService;

import java.io.IOException;

/**Тестирование приватных методов??? Как запустить модульные тесты отдельно от интеграционных?*/
@SpringBootTest
@ActiveProfiles("test")
class LinkServiceImplTest {

    private final LinkService linkService;
    private final LinkRepository linkRepository;

    private static final String shortLink = "cGoLOOe.mg";
    private static final String fullLink = "https://www.google.com";
    private static final String badLink = "https://www.google.com/hkjhk";

    /**Почему без аннотации не поднимаются тесты. Предполагаю что для тестов создается свой IoC контейнер.
     * В нем не будет бинов которые создаются при старте основного приложения*/
    @Autowired
    public LinkServiceImplTest(LinkService linkService, LinkRepository linkRepository) {
        this.linkService = linkService;
        this.linkRepository = linkRepository;
    }

    @AfterEach
    void deleteDatabaseValueAfterTest() {
        linkRepository.deleteAll();
    }

    @Test
    void testCreateShortLink() {
        String createdShortLink = linkService.createShortLink(fullLink);
        Assertions.assertEquals(shortLink, createdShortLink);
    }

    @Test
    void testCreateShortLinkNegative() {
        Assertions.assertThrows(WrongLinkException.class,
                () -> linkService.createShortLink(badLink));
    }

    /**
     * Надо ли в тесте действие по созданию линка? А если его не будет @BeforeEch очистит БД.
     * Есть ли метод кторый выполнит это действие для всех методов, которым это необходимо?
     * */
    @Test
    void testDeleteLinkIfExists() {
        String shortLink = linkService.createShortLink(fullLink);
        linkService.deleteLink(shortLink);
    }

    @Test
    void testDeleteLinkIfNotExists(){
        Assertions.assertThrows(WrongLinkException.class,
                () -> linkService.deleteLink(shortLink));
    }

    /**Не понятно почему в аннонимном классе дергаем сервис, почему нельзя его дернуть напрямую
     * Предпологаю что аннонимный класс является оберткой и в нем происходит проверка на
     * выброшенный эксепшен.
     * */
    /**Разобраться с аргументами метода assertThrows()*/
    @Test
    void getStatisticsIfNotExists() {
        Assertions.assertThrows(WrongLinkException.class,
                () -> linkService.getStatistics(shortLink));
    }


    /**Разобраться будет ли работать с транзакцией если нет то почему
     * Возможно если делали бы полный апдейт сущности то сработало*/
    @Test
    void testRedirectAndIncrement() throws IOException {
        MockHttpServletResponse response = new MockHttpServletResponse();

        String shortLink = linkService.createShortLink(fullLink);
        Assertions.assertEquals(0, linkRepository.findById(shortLink).get().getVisits());

        linkService.redirect(shortLink, response);
        Assertions.assertEquals(302, response.getStatus());
        Assertions.assertEquals(1,linkRepository.findById(shortLink).get().getVisits());
    }
}