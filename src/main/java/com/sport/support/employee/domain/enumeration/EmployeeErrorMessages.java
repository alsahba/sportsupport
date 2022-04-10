package com.sport.support.employee.domain.enumeration;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeErrorMessages implements BusinessRuleErrorMessage {

   ERROR_EMPLOYEE_IS_NOT_FOUND("SSEE-1", "ERROR_EMPLOYEE_IS_NOT_FOUND"),
   ERROR_EMPLOYEE_TRAINER_IS_UNAUTHORIZED("SSEE-2","ERROR_EMPLOYEE_TRAINER_IS_UNAUTHORIZED"),
   ERROR_EMPLOYEE_MANAGER_IS_UNAUTHORIZED("SSEE-3","ERROR_EMPLOYEE_MANAGER_IS_UNAUTHORIZED");

   private final String code;
   private final String message;

}
