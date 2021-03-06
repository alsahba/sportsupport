package com.sport.support.shared.exception;

import lombok.Getter;

@Getter
public class BusinessRuleException extends RuntimeException {

   private final String code;

   public BusinessRuleException(BusinessRuleErrorMessage businessRuleErrorMessage) {
      super(businessRuleErrorMessage.getMessage());
      this.code = businessRuleErrorMessage.getCode();
   }
}
