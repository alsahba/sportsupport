package com.sport.support.infrastructure.common;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
@Builder
public class Money {

   @Column(name = "AMOUNT")
   private double amount;

   public String format() {
      BigDecimal moneyWithFractionalPart = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
      return moneyWithFractionalPart.toPlainString();
   }
}
