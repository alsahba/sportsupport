package com.sport.support.employee.domain.enumeration;

import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum EmployeeType {
   TRAINER(RoleEnum.TRAINER),
   MANAGER(RoleEnum.MANAGER);

   private final RoleEnum role;
}
