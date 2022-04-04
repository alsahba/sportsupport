package com.sport.support.branch.domain;

import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.branch.domain.vo.PaymentId;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

@Getter
@SuperBuilder
public class Payment extends AbstractDomainObject<PaymentId> {

   private final BranchId branchId;
   private final Money cost;
   private final Duration duration;
   private final Type type;

}
