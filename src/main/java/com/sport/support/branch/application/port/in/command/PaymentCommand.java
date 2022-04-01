package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.in.web.payload.AddUpdatePaymentRequest;
import com.sport.support.branch.domain.Payment;
import com.sport.support.shared.common.money.Money;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Getter;

@Getter
public class PaymentCommand {

   private final Type type;
   private final Duration duration;
   private final Money cost;

   public PaymentCommand(AddUpdatePaymentRequest request) {
      this.type = request.getType();
      this.duration = request.getDuration();
      this.cost = Money.of(request.getCost());
   }

   public Payment toDomain() {
      return Payment.builder()
          .type(type)
          .duration(duration)
          .cost(cost)
          .build();
   }
}
