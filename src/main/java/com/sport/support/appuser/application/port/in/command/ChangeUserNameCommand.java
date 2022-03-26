package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.in.web.payload.ChangeUserNameRequest;
import lombok.Getter;

@Getter
public class ChangeUserNameCommand {

   private final Long userId;
   private final String name;
   private final String surname;

   public ChangeUserNameCommand(Long userId, ChangeUserNameRequest request) {
      this.userId = userId;
      this.name = request.getName();
      this.surname = request.getSurname();
   }
}
