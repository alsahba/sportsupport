package com.sport.support.infrastructure.security.filter;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor
public class BearerTokenAuthorizationFilter extends OncePerRequestFilter {

    private final String prefix;
    private final String secretKey;
    private final LoadUserUC loadUserUC;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws ServletException, IOException {
        String authorizationHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (StringUtils.hasLength(authorizationHeader) && authorizationHeader.startsWith(prefix)) {
            String token = authorizationHeader.substring(7);
            Long userId = getIdFromJWT(token);

            var user = loadUserUC.loadUserDetailsById(userId);

            UsernamePasswordAuthenticationToken authenticationToken
                    = new UsernamePasswordAuthenticationToken(userId, null, user.getAuthorities());
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }

        chain.doFilter(request, response);
    }

    private Long getIdFromJWT(String token) {
        Jws<Claims> claimsJws = Jwts.parser()
                .setSigningKey(secretKey.getBytes(StandardCharsets.UTF_8))
                .parseClaimsJws(token);
        return Long.valueOf(claimsJws.getBody().getSubject());
    }
}
