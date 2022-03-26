package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.in.web.payload.ChangePasswordRequest;
import lombok.Getter;

@Getter
public class ChangePasswordCommand {

   private final Long id;
   private final String password;
   private final String newPassword;

   public ChangePasswordCommand(Long id, ChangePasswordRequest request) {
      this.id = id;
      this.password = request.getOldPassword();
      this.newPassword = request.getNewPassword();
   }
}
