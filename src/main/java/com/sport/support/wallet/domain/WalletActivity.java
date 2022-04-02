package com.sport.support.wallet.domain;

import com.sport.support.shared.common.money.Money;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
public class WalletActivity {

   private Long id;
   private Long walletId;
   private WalletActivityType type;
   private Money amount;
   private LocalDateTime transactionDate;

}
