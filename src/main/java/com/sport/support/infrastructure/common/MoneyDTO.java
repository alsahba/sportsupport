package com.sport.support.infrastructure.common;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@NoArgsConstructor
public class MoneyDTO {

   @NotBlank
   private String currency;

   @NotNull
   @Min(0)
   private BigDecimal amount;

   public MoneyDTO(Money money) {
      setAmount(BigDecimal.valueOf(money.getAmount()));
      setCurrency("TRY");
   }
}
