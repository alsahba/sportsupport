package com.sport.support.appuser.application.port.in.command;

import com.sport.support.shared.security.enumeration.RoleEnum;

public record UpdateRoleCommand(Long id, RoleEnum role) {
}
