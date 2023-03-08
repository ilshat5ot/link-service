package ru.sadykov.link.authenticationservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = {"ru.sadykov.link.common.repository"})
@EntityScan(basePackages = {"ru.sadykov.link.common.entity"})
public class LinkAuthenticationService {
    public static void main(String[] args) {
        SpringApplication.run(LinkAuthenticationService.class, args);
    }
}
