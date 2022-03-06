package com.sport.support.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class JwtEmailPasswordAuthFilter extends AbstractAuthenticationProcessingFilter {

    private final String secretKey;
    private final long ttl;
    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER = new AntPathRequestMatcher("/login", "POST");

    public JwtEmailPasswordAuthFilter(
            AuthenticationManager authenticationManager,
            String secretKey,
            long ttl) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
        this.secretKey = secretKey;
        this.ttl = ttl;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest loginRequest;
        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            EmailPasswordAuthToken emailPasswordAuthToken
                    = new EmailPasswordAuthToken(loginRequest.getEmail(), loginRequest.getPassword());

            return getAuthenticationManager().authenticate(emailPasswordAuthToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        String token = Jwts.builder()
                .setSubject(((AppUserDetails) authResult.getPrincipal()).getAppUser().getId().toString())
                .claim("authorities", authResult.getAuthorities())
                .setIssuedAt(new Date())
                .setExpiration(convertLocalDateTimeToDate(LocalDateTime.now().plusSeconds(ttl)))
                .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                .compact();
        response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
    }

    private Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
