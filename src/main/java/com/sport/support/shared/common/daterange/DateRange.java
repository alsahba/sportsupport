package com.sport.support.shared.common.daterange;

import com.sport.support.shared.exception.BusinessRuleException;

import javax.validation.constraints.AssertTrue;
import java.time.LocalDateTime;

public record DateRange(LocalDateTime from, LocalDateTime to) {

   @AssertTrue
   public boolean isValid() {
      if (from.isAfter(to)) {
         throw new BusinessRuleException(DateRangeErrorMessages.ERROR_RANGE_START_DATE_LATER_END_DATE);
      }
      if (from.isAfter(LocalDateTime.now())) {
         throw new BusinessRuleException(DateRangeErrorMessages.ERROR_RANGE_START_DATE_LATER_THAN_NOW);
      }
      if (to.getMonth().minus(from.getMonth().getValue()).getValue() > 6) {
         throw new BusinessRuleException(DateRangeErrorMessages.ERROR_RANGE_IS_BIGGER_THAN_6_MONTHS);
      }
      return true;
   }

}
