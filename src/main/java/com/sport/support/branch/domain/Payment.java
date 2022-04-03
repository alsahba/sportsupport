package com.sport.support.branch.domain;

import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.branch.domain.vo.PaymentId;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import lombok.Builder;
import lombok.Getter;

@Getter
public class Payment extends AbstractDomainObject<PaymentId> {

   private BranchId branchId;
   private Money cost;
   private Duration duration;
   private Type type;

   @Builder
   public Payment(PaymentId idVO, BranchId branchId, Money cost, Duration duration, Type type) {
      super(idVO);
      this.branchId = branchId;
      this.cost = cost;
      this.duration = duration;
      this.type = type;
   }
}
