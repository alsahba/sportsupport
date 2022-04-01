package com.sport.support.shared.security.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sport.support.shared.security.token.EmailPasswordAuthToken;
import com.sport.support.shared.security.configuration.JwtProperties;
import com.sport.support.shared.security.dto.LoginRequest;
import com.sport.support.appuser.domain.AppUserDetails;
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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Date;

public class CustomAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

   private final String secretKey;
   private final long accessTokenExpireTime;
   private final long refreshTokenExpireTime;

   public CustomAuthenticationFilter(AuthenticationManager authenticationManager, JwtProperties jwtProperties) {
      super(authenticationManager);
      this.secretKey = jwtProperties.getSecret();
      this.accessTokenExpireTime = jwtProperties.getExpireTime();
      this.refreshTokenExpireTime = jwtProperties.getRefreshTime();
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
   protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authResult) {
      var userId = (((AppUserDetails) authResult.getPrincipal()).getAppUser().getId()).toString();
      response.addHeader(HttpHeaders.AUTHORIZATION, "access_token " + createAccessToken(userId));
      response.addHeader(HttpHeaders.AUTHORIZATION, "refresh_token " + createRefreshToken(userId));
   }

   private String createAccessToken(String userId) {
      return Jwts.builder()
          .setSubject(userId)
          .setExpiration(new Date(System.currentTimeMillis() + accessTokenExpireTime))
          .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
          .compact();
   }

   private String createRefreshToken(String userId) {
      return Jwts.builder()
          .setSubject(userId)
          .setExpiration(new Date(System.currentTimeMillis() + refreshTokenExpireTime))
          .signWith(SignatureAlgorithm.HS512, secretKey.getBytes(StandardCharsets.UTF_8))
          .compact();
   }
}
