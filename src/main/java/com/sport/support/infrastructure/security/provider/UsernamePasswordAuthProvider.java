package com.sport.support.infrastructure.security.provider;

import com.sport.support.infrastructure.security.configuration.AppPasswordEncoder;
import com.sport.support.appuser.application.service.AppUserDetailsManager;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;

@Configuration
@RequiredArgsConstructor
public class UsernamePasswordAuthProvider implements AuthenticationProvider {

   private final AppUserDetailsManager appUserDetailsManager;
   private final AppPasswordEncoder appPasswordEncoder;

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      var username = authentication.getName();
      var password = authentication.getCredentials().toString();

      UserDetails user = appUserDetailsManager.loadUserByUsername(username);

      if (!appPasswordEncoder.matches(password, user.getPassword())) {
         throw new BadCredentialsException("Bad credentials");
      }

      return new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
   }

   @Override
   public boolean supports(Class<?> authType) {
      return authType.equals(UsernamePasswordAuthenticationToken.class);
   }
}
