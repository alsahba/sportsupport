package com.sport.support.appuser.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AppUser {

   private Long id;
   private String name;
   private String surname;
   private String username;
   private String password;
   private String email;
   private String phone;
   private Role role;

}
