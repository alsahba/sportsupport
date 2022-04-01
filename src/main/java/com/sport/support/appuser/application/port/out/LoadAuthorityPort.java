package com.sport.support.appuser.application.port.out;

import com.sport.support.appuser.domain.Role;
import com.sport.support.shared.security.enumeration.RoleEnum;

public interface LoadAuthorityPort {

   Role loadRole(RoleEnum role);

}