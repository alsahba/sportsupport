package com.sport.support.membership.domain.enumeration;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MembershipErrorMessages implements BusinessRuleErrorMessage {

   ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER("SSEM-1","ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER"),
   ERROR_MEMBERSHIP_IS_ALREADY_CANCELLED("SSEM-2", "ERROR_MEMBERSHIP_IS_ALREADY_CANCELLED"),
   ERROR_MEMBERSHIP_IS_NOT_FOUND("SSEM-3", "ERROR_MEMBERSHIP_IS_NOT_FOUND"),
   ERROR_MEMBERSHIP_TRAINER_IS_UNAUTHORIZED("SSEM-3","ERROR_MEMBERSHIP_TRAINER_IS_UNAUTHORIZED");

   private final String code;
   private final String message;

   @Override
   public String getCode() {
      return code;
   }

   @Override
   public String getMessage() {
      return message;
   }
}
