package com.sport.support.branch.domain;

import com.sport.support.shared.common.money.Money;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class Payment {

   private Long id;
   private Long branchId;
   private Money cost;
   private Duration duration;
   private Type type;

}
