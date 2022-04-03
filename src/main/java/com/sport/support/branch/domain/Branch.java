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
import lombok.Builder;
import lombok.Getter;

import java.util.Set;

@Getter
public class Branch extends AbstractDomainObject<BranchId> {

   private String name;
   private int quota;
   private CityId cityId;
   private DistrictId districtId;
   private String address;
   private String phoneNumber;
   private Set<Payment> payments;

   @Builder
   public Branch(BranchId idVO, String name, int quota, CityId cityId, DistrictId districtId, String address, String phoneNumber, Set<Payment> payments) {
      super(idVO);
      this.name = name;
      this.quota = quota;
      this.cityId = cityId;
      this.districtId = districtId;
      this.address = address;
      this.phoneNumber = phoneNumber;
      this.payments = payments;
   }

   public Money getCost(Type type, Duration duration) {
      var payment = getPayments().stream()
          .filter(p -> p.getType().equals(type) && p.getDuration().equals(duration))
          .findFirst()
          .orElseThrow(() -> new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_PAYMENT_INFO_IS_NOT_FOUND));
      return payment.getCost();
   }

}
