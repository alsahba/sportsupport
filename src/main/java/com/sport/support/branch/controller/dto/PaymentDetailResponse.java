package com.sport.support.branch.controller.dto;

import com.sport.support.branch.entity.Payment;
import com.sport.support.infrastructure.common.MoneyDTO;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentDetailResponse {

   private MoneyDTO cost;
   private Type type;

   public PaymentDetailResponse(Payment payment) {
      setType(payment.getType());
      setCost(new MoneyDTO(payment.getCost()));
   }
}
