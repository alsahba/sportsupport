package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.branch.adapter.out.persistence.Payment;
import com.sport.support.infrastructure.common.MoneyDTO;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
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
