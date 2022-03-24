package com.sport.support.appuser.adapter.in.web.payload;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import lombok.Data;

@Data
public class UserDetailResponse {

   private String name;
   private String surname;
   private String email;

   public UserDetailResponse(AppUser appUser) {
      name = appUser.getName();
      surname = appUser.getSurname();
      email = appUser.getEmail();
   }

}
