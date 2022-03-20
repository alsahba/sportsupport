package com.sport.support.infrastructure.common;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class Money implements Serializable {

   @Column(name = "AMOUNT")
   private double amount;

   public String format() {
      BigDecimal moneyWithFractionalPart = BigDecimal.valueOf(amount).divide(BigDecimal.valueOf(100), 2, RoundingMode.HALF_UP);
      return moneyWithFractionalPart.toPlainString();
   }

   public static Money of(double amount) {
      return new Money(amount);
   }

   public static Money of(BigDecimal amount) {
      return of(amount.doubleValue());
   }

   public static Money zero() {
      return new Money(0);
   }

   public Money add(Money money) {
      return new Money(amount + money.amount);
   }

   public Money subtract(Money money) {
      return new Money(amount - money.amount);
   }

   public Money multiply(double multiplier) {
      return new Money(amount * multiplier);
   }

   public Money divide(double divider) {
      return new Money(amount / divider);
   }

   public boolean isGreaterThan(Money money) {
      return amount > money.amount;
   }

   public boolean isLessThan(Money money) {
      return amount < money.amount;
   }

   public boolean isEqual(Money money) {
      return amount == money.amount;
   }

   public boolean isGreaterThanOrEqual(Money money) {
      return amount >= money.amount;
   }

   public boolean isLessThanOrEqual(Money money) {
      return amount <= money.amount;
   }


}
