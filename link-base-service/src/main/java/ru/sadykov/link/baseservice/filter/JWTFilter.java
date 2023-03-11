package ru.sadykov.link.baseservice.filter;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
public class JWTFilter extends OncePerRequestFilter {
    private static final String TOKEN_PREFIX = "Bearer ";
    private static final String AUTHORITIES = "authorities";
    private final String secretKey;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response,
                                    FilterChain filterChain) throws ServletException, IOException {
        String authorizationHeaderValue = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (authorizationHeaderValue == null || !authorizationHeaderValue.startsWith(TOKEN_PREFIX)) {
            filterChain.doFilter(request, response);
            logger.error("Header is not start with bearer");
            return;
        }
        String jwtToken = authorizationHeaderValue.replace(TOKEN_PREFIX, "");
        Jws<Claims> claimsJws = Jwts.parser().setSigningKey(secretKey).parseClaimsJws(jwtToken);
        Claims body = claimsJws.getBody();
        List<Map<String, String>> authorities = (List<Map<String, String>>) body.get(AUTHORITIES);
        Set<SimpleGrantedAuthority> roles = authorities.stream().map(authority ->
                new SimpleGrantedAuthority(authority.get("authority"))).collect(Collectors.toSet());

        Authentication authenticationToken
                = new UsernamePasswordAuthenticationToken(body.getSubject(), null, roles);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);

        filterChain.doFilter(request, response);
    }
}
