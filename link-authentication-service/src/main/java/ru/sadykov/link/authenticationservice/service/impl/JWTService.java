package ru.sadykov.link.authenticationservice.service.impl;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.Date;

/**Сделать интерфейс*/
@Service
public class JWTService {

    private static final String AUTHORITIES = "authorities";

    @Value("${jwt.secret}")
    private String secretKey;

    @Value("${jwt.sessionTime}")
    private long sessionTime;

    public String createToken(UserDetails userDetails) {

        return Jwts.builder()
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expireTimeFromNow())
                .claim(AUTHORITIES, userDetails.getAuthorities())
                .signWith(SignatureAlgorithm.HS256, secretKey).compact();
    }

    private Date expireTimeFromNow() {
        return new Date(System.currentTimeMillis() + sessionTime);
    }
}
