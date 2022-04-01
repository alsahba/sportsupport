package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.in.web.payload.AddUserRequest;
import com.sport.support.appuser.domain.AppUser;
import lombok.Getter;

@Getter
public class RegisterUserCommand {

   private final String name;
   private final String surname;
   private final String username;
   private final String password;
   private final String phone;
   private final String email;

   public RegisterUserCommand(AddUserRequest request) {
      this.name = request.getName();
      this.surname = request.getSurname();
      this.username = request.getUsername();
      this.password = request.getPassword();
      this.phone = request.getPhoneNumber();
      this.email = request.getEmail();
   }

   public AppUser toDomain() {
      return AppUser.builder()
          .name(name)
          .surname(surname)
          .username(username)
          .password(password)
          .phone(phone)
          .email(email)
          .build();
   }
}
