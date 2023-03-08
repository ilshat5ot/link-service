package ru.sadykov.link.baseservice.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import ru.sadykov.link.baseservice.filter.JWTFilter;

@Configuration
@EnableWebSecurity(debug = true)
@EnableMethodSecurity
public class LinkServiceConfig {

    @Value("${jwt.secret}")
    private String secretKey;
    /**SessionCreationPolicy.STATELESS разобраться*/
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.csrf().disable().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and().addFilterAfter(new JWTFilter(secretKey), UsernamePasswordAuthenticationFilter.class)
                .authorizeHttpRequests().anyRequest().authenticated();
        return http.build();
    }


}
