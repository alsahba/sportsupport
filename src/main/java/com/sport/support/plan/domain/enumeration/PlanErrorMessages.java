package com.sport.support.plan.domain.enumeration;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanErrorMessages implements BusinessRuleErrorMessage {

   ERROR_PLAN_IS_NOT_FOUND("SSEP-1", "ERROR_PLAN_IS_NOT_FOUND"),
   ERROR_PLAN_DATE_IS_NOT_VALID("SSEP-2", "ERROR_PLAN_DATE_IS_NOT_VALID");

   private final String code;
   private final String message;

}
