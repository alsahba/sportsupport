package com.sport.support.infrastructure.security.provider;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.infrastructure.security.configuration.AppPasswordEncoder;
import com.sport.support.infrastructure.security.token.EmailPasswordAuthToken;
import com.sport.support.infrastructure.security.user.AppUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@RequiredArgsConstructor
public class EmailPasswordAuthProvider implements AuthenticationProvider {

   private final LoadUserUC loadUserUC;
   private final AppPasswordEncoder appPasswordEncoder;

   @Override
   public Authentication authenticate(Authentication authentication) throws AuthenticationException {
      var email = authentication.getName();
      var password = authentication.getCredentials().toString();
      var user = new AppUserDetails(loadUserUC.loadByEmail(email));

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