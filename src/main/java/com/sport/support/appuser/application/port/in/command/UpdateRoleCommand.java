package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import lombok.Getter;

@Getter
public class UpdateRoleCommand {

   private final AppUser user;
   private final RoleEnum role;

   public UpdateRoleCommand(AppUser user, RoleEnum role) {
      this.user = user;
      this.role = role;
   }
}
