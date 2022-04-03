package com.sport.support.wallet.domain;

import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import com.sport.support.wallet.domain.vo.WalletActivityId;
import com.sport.support.wallet.domain.vo.WalletId;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class WalletActivity extends AbstractDomainObject<WalletActivityId> {

   private WalletId walletId;
   private WalletActivityType type;
   private Money amount;
   private LocalDateTime transactionDate;

   @Builder
   public WalletActivity(WalletActivityId idVO, WalletId walletId, WalletActivityType type, Money amount, LocalDateTime transactionDate) {
      super(idVO);
      this.walletId = walletId;
      this.type = type;
      this.amount = amount;
      this.transactionDate = transactionDate;
   }
}
