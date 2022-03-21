package com.sport.support.infrastructure.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDTO {

   private String currency;
   private BigDecimal amount;

   public MoneyDTO(Money money) {
      setAmount(BigDecimal.valueOf(money.getAmount()));
      setCurrency("TRY");
   }
}
