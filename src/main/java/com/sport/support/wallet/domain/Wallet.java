package com.sport.support.wallet.domain;

import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import com.sport.support.wallet.domain.enumeration.WalletErrorMessages;
import com.sport.support.wallet.domain.vo.WalletId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.Set;

@Getter
@Setter
public class Wallet extends AbstractDomainObject<WalletId> {

   private Long userId;
   private Money balance;
   private Money totalSpent;
   private Set<WalletActivity> activities;

   @Builder
   public Wallet(WalletId idVO, Long userId, Money balance, Money totalSpent, Set<WalletActivity> activities) {
      super(idVO);
      this.userId = userId;
      this.balance = balance;
      this.totalSpent = totalSpent;
      this.activities = activities;
   }

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
          .walletId(new WalletId(getId()))
          .transactionDate(LocalDateTime.now())
          .amount(change)
          .build();
   }
}
