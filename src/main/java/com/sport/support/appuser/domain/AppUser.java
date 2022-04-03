package com.sport.support.appuser.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AppUser extends AbstractDomainObject<UserId> {

   private String name;
   private String surname;
   private String username;
   private String password;
   private String email;
   private String phone;
   private Role role;

   @Builder
   public AppUser(UserId idVO, String name, String surname, String username, String password, String email, String phone, Role role) {
      super(idVO);
      this.name = name;
      this.surname = surname;
      this.username = username;
      this.password = password;
      this.email = email;
      this.phone = phone;
      this.role = role;
   }
}
