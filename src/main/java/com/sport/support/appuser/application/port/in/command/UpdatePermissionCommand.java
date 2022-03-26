package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import lombok.Getter;

import java.util.Set;

@Getter
public class UpdatePermissionCommand {

   private final AppUser user;
   private final Set<String> permissions;

   public UpdatePermissionCommand(AppUser user, RoleEnum role) {
      this.user = user;
      this.permissions = role.getPermissions();
   }
}
