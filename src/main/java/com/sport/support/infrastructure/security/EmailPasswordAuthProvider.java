package com.sport.support.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@RequiredArgsConstructor
public class EmailPasswordAuthProvider implements AuthenticationProvider {

    private final AppUserDetailsManager appUserDetailsManager;
    private final AppPasswordEncoder appPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = appUserDetailsManager.loadUserByEmail(email);

        if (!appPasswordEncoder.matches(password, user.getPassword())) {
            throw new BadCredentialsException("Bad credentials");
        }

        return new EmailPasswordAuthToken(user, null, user.getAuthorities());
    }

    @Override
    public boolean supports(Class<?> authType) {
        return authType.equals(EmailPasswordAuthToken.class);
    }
}