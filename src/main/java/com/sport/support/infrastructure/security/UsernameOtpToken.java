package com.sport.support.infrastructure.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class UsernameOtpToken extends AbstractAuthenticationToken {

    private final Object principal;
    private final Object credentials;

    public UsernameOtpToken(Object username, Object otp) {
        super(Collections.emptyList());
        this.principal = username;
        this.credentials = otp;
        setAuthenticated(false);
    }

    public UsernameOtpToken(Object username, Object otp, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = username;
        this.credentials = otp;
        setAuthenticated(false);
    }

    @Override
    public Object getCredentials() {
        return credentials;
    }

    @Override
    public Object getPrincipal() {
        return principal;
    }
}
