package com.sport.support.wallet.domain;

import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Wallet {

   private Long id;
   private Long userId;
   private Money balance;
   private Money totalSpent;

   public void withdraw(Money change) {
      setBalance(getBalance().subtract(change));
      setTotalSpent(getTotalSpent().add(change));
      if (getBalance().isNegative()) {
         throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
      }
   }

   public void deposit(Money change) {
      setBalance(getBalance().add(change));
   }
}
