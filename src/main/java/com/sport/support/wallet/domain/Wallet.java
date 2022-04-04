package com.sport.support.wallet.domain;

import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import com.sport.support.wallet.domain.enumeration.WalletErrorMessages;
import com.sport.support.wallet.domain.vo.WalletId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@SuperBuilder
public class Wallet extends AbstractDomainObject<WalletId> {

   private Long userId;
   private Money balance;
   private Money totalSpent;
   private Set<WalletActivity> activities;

   public void withdraw(Money change) {
      this.balance = this.balance.subtract(change);
      this.totalSpent = this.totalSpent.add(change);
      this.activities = Set.of(createActivity(WalletActivityType.WITHDRAW, change));
      if (getBalance().isNegative()) {
         throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
      }
   }

   public void deposit(Money change) {
      this.balance = this.balance.add(change);
      this.activities = Set.of(createActivity(WalletActivityType.DEPOSIT, change));
   }

   private WalletActivity createActivity(WalletActivityType type, Money change) {
      return WalletActivity.builder()
          .type(type)
          .walletId(new WalletId(getId()))
          .transactionDate(LocalDateTime.now())
          .amount(change)
          .build();
   }
}
