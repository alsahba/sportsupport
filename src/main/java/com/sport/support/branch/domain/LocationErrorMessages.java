package com.sport.support.branch.domain;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationErrorMessages implements BusinessRuleErrorMessage {

   ERROR_CITY_IS_NOT_FOUND("SSEL-1", "ERROR_CITY_IS_NOT_FOUND"),
   ERROR_DISTRICT_IS_NOT_FOUND("SSEL-2", "ERROR_DISTRICT_IS_NOT_FOUND");

   private final String code;
   private final String message;

}
