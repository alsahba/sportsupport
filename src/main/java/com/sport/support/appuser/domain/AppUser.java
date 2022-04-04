package com.sport.support.appuser.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class AppUser extends AbstractDomainObject<UserId> {

   private String name;
   private String surname;
   private String username;
   private String password;
   private String email;
   private String phone;
   private Role role;

   public void update(String password, Role role) {
      this.password = password;
      this.role = role;
   }
}
