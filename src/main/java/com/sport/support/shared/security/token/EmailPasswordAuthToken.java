package com.sport.support.shared.security.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.Collections;

public class EmailPasswordAuthToken extends AbstractAuthenticationToken {

   private final Object principal;
   private final Object credentials;

   public EmailPasswordAuthToken(Object email, Object password) {
      super(Collections.emptyList());
      this.principal = email;
      this.credentials = password;
   }

   public EmailPasswordAuthToken(Object email, Object password, Collection<? extends GrantedAuthority> authorities) {
      super(authorities);
      this.principal = email;
      this.credentials = password;
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
