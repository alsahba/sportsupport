package com.sport.support.infrastructure.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDTO {

   private String currency;
   private String amount;

   public MoneyDTO(Money money) {
      setAmount(money.format());
      setCurrency("TRY");
   }
}
