package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.in.web.payload.AddUserRequest;
import lombok.Getter;

@Getter
public class RegisterUserCommand {

   private final String name;
   private final String surname;
   private final String username;
   private final String password;
   private final String phoneNumber;
   private final String email;

   public RegisterUserCommand(AddUserRequest request) {
      this.name = request.getName();
      this.surname = request.getSurname();
      this.username = request.getUsername();
      this.password = request.getPassword();
      this.phoneNumber = request.getPhoneNumber();
      this.email = request.getEmail();
   }
}
