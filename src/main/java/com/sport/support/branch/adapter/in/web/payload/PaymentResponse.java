package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.branch.domain.Payment;
import com.sport.support.infrastructure.common.money.MoneyDTO;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Getter;

@Getter
public class PaymentResponse {

   private final MoneyDTO cost;
   private final Type type;
   private final Duration duration;

   public PaymentResponse(Payment payment) {
      this.cost = new MoneyDTO(payment.getCost());
      this.type = payment.getType();
      this.duration = payment.getDuration();
   }
}
