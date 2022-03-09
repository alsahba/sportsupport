package com.sport.support.infrastructure.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.service.OtpService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private final String secretKey;
    private final long ttl;
    private final OtpService otpService;

    public CustomAuthenticationFilter(
            AuthenticationManager authenticationManager,
            OtpService otpService,
            String secretKey,
            long ttl) {
        super(authenticationManager);
        this.secretKey = secretKey;
        this.ttl = ttl;
        this.otpService = otpService;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        LoginRequest loginRequest;
        AbstractAuthenticationToken authenticationToken;

        try {
            loginRequest = new ObjectMapper().readValue(request.getInputStream(), LoginRequest.class);
            if (StringUtils.hasLength(loginRequest.getUsername()) && StringUtils.hasLength(loginRequest.getPassword())) {
                authenticationToken = new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword());
            } else if (StringUtils.hasLength(loginRequest.getEmail()) && StringUtils.hasLength(loginRequest.getPassword())) {
                authenticationToken = new EmailPasswordAuthToken(loginRequest.getEmail(), loginRequest.getPassword());
            } else if (StringUtils.hasLength(loginRequest.getUsername()) && StringUtils.hasLength(loginRequest.getOtp())) {
                authenticationToken = new UsernameOtpToken(loginRequest.getUsername(), loginRequest.getOtp());
            } else {
                throw new BadCredentialsException("Invalid request");
            }
            return getAuthenticationManager().authenticate(authenticationToken);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) throws IOException, ServletException {
        AppUser appUser = ((AppUserDetails) authResult.getPrincipal()).getAppUser();
        if (!(authResult instanceof UsernameOtpToken)) {
            response.setHeader("otp_code", otpService.save(appUser.getUsername()));
        } else {
            String token = Jwts.builder()
                    .setSubject(appUser.getId().toString())
                    .claim("authorities", authResult.getAuthorities())
                    .setIssuedAt(new Date())
                    .setExpiration(convertLocalDateTimeToDate(LocalDateTime.now().plusSeconds(ttl)))
                    .signWith(SignatureAlgorithm.HS256, secretKey.getBytes(StandardCharsets.UTF_8))
                    .compact();
            response.addHeader(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }
    }

    private Date convertLocalDateTimeToDate(LocalDateTime localDateTime) {
        return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
    }
}
