package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.in.web.payload.AddUpdatePaymentRequest;
import com.sport.support.infrastructure.common.money.MoneyDTO;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import lombok.Getter;

@Getter
public class PaymentCommand {

   private final Type type;
   private final Duration duration;
   private final MoneyDTO cost;

   public PaymentCommand(AddUpdatePaymentRequest request) {
      this.type = request.getType();
      this.duration = request.getDuration();
      this.cost = new MoneyDTO(request.getCost());
   }
}
