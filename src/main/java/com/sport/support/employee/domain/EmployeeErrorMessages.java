package com.sport.support.employee.domain;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum EmployeeErrorMessages implements BusinessRuleErrorMessage {

   ERROR_EMPLOYEE_IS_NOT_FOUND("SSEE-1", "ERROR_EMPLOYEE_IS_NOT_FOUND");

   private final String code;
   private final String message;

}
