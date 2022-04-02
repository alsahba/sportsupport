package com.sport.support.wallet.domain;

import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@Builder
public class Wallet {

   private Long id;
   private Long userId;
   private Money balance;
   private Money totalSpent;
   private Set<WalletActivity> activities;

   public void withdraw(Money change) {
      setBalance(getBalance().subtract(change));
      setTotalSpent(getTotalSpent().add(change));
      if (getBalance().isNegative()) {
         throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
      }
      setActivities(Set.of(createActivity(WalletActivityType.WITHDRAW, change)));
   }

   public void deposit(Money change) {
      setBalance(getBalance().add(change));
      setActivities(Set.of(createActivity(WalletActivityType.DEPOSIT, change)));
   }

   private WalletActivity createActivity(WalletActivityType type, Money change) {
      return WalletActivity.builder()
          .type(type)
          .walletId(getId())
          .transactionDate(LocalDateTime.now())
          .amount(change)
          .build();
   }
}
