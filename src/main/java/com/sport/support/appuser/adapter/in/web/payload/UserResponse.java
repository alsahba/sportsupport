package com.sport.support.appuser.adapter.in.web.payload;

import com.sport.support.appuser.domain.AppUser;
import lombok.Data;

@Data
public class UserResponse {

   private String name;
   private String surname;
   private String username;
   private String email;

   public UserResponse(AppUser appUser) {
      name = appUser.getName();
      surname = appUser.getSurname();
      username = appUser.getUsername();
      email = appUser.getEmail();
   }
}
