package com.sport.support.appuser.application.port.in.command;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;

public record UpdateRoleCommand(AppUser user, RoleEnum role) {
}
