package com.sport.support.infrastructure.security;

import com.sport.support.appuser.service.OtpService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@RequiredArgsConstructor
public class OtpAuthProvider implements AuthenticationProvider {

    private final AppUserDetailsManager appUserDetailsManager;
    private final OtpService otpService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String otp = (String) authentication.getCredentials();
        String username = (String) authentication.getPrincipal();

        if (otpService.validateOtp(otp, username)) {
            UserDetails user = appUserDetailsManager.loadUserByUsername(username);
            return new UsernameOtpToken(user, null, user.getAuthorities());
        }
        throw new RuntimeException("Invalid OTP");
    }

    @Override
    public boolean supports(Class<?> authType) {
        return authType.equals(UsernameOtpToken.class);
    }
}
