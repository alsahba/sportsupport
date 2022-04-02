package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.shared.common.money.MoneyDTO;
import com.sport.support.wallet.domain.WalletActivity;
import com.sport.support.wallet.domain.WalletActivityType;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class WalletActivityResponse {

   private Long walletId;
   private WalletActivityType type;
   private MoneyDTO amount;
   private LocalDateTime transactionDate;

   public WalletActivityResponse(WalletActivity walletActivity) {
      this.walletId = walletActivity.getWalletId();
      this.type = walletActivity.getType();
      this.amount = walletActivity.getAmount().toDTO();
      this.transactionDate = walletActivity.getTransactionDate();
   }
}
