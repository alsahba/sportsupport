package com.sport.support.plan.domain;

import com.sport.support.infrastructure.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum PlanErrorMessages implements BusinessRuleErrorMessage {

   ERROR_PLAN_IS_NOT_FOUND("SSEP-1", "ERROR_PLAN_IS_NOT_FOUND");

   private final String code;
   private final String message;

}
