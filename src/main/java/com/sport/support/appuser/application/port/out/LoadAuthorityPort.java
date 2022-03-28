package com.sport.support.appuser.application.port.out;

import com.sport.support.appuser.adapter.out.persistence.entity.Role;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;

public interface LoadAuthorityPort {
   Role loadRole(RoleEnum role);
}