package com.sport.support.branch.domain;

import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
@Builder
public class Branch {

   private Long id;
   private String name;
   private int quota;
   private Long cityId;
   private Long districtId;
   private String address;
   private String phoneNumber;
   private Set<Payment> payments;

   public Money getCost(Type type, Duration duration) {
      var payment = getPayments().stream()
          .filter(p -> p.getType().equals(type) && p.getDuration().equals(duration))
          .findFirst()
          .orElseThrow(() -> new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND));
      return payment.getCost();
   }

}
