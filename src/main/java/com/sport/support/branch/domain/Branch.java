package com.sport.support.branch.domain;

import com.sport.support.branch.domain.enumeration.BranchErrorMessages;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.branch.domain.vo.CityId;
import com.sport.support.branch.domain.vo.DistrictId;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.util.Set;

@Getter
@SuperBuilder
public class Branch extends AbstractDomainObject<BranchId> {

   private final String name;
   private final int quota;
   private final CityId cityId;
   private final DistrictId districtId;
   private final String address;
   private final String phoneNumber;
   private final Set<Payment> payments;

   public Money getCost(Type type, Duration duration) {
      var payment = getPayments().stream()
          .filter(p -> p.getType().equals(type) && p.getDuration().equals(duration))
          .findFirst()
          .orElseThrow(() -> new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND));
      return payment.getCost();
   }

}
