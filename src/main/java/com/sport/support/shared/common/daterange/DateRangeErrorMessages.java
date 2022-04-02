package com.sport.support.shared.common.daterange;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum DateRangeErrorMessages implements BusinessRuleErrorMessage {

   ERROR_RANGE_START_DATE_LATER_THAN_NOW("SSEDR-1", "ERROR_RANGE_START_DATE_LATER_THAN_NOW"),
   ERROR_RANGE_START_DATE_LATER_END_DATE("SSEDR-2", "ERROR_RANGE_START_DATE_LATER_END_DATE"),
   ERROR_RANGE_IS_BIGGER_THAN_6_MONTHS("SSEDR-3", "ERROR_RANGE_IS_BIGGER_THAN_6_MONTHS");

   private final String code;
   private final String message;

}
