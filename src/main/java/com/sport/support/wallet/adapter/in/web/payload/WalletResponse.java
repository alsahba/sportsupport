package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.shared.common.money.MoneyDTO;
import com.sport.support.wallet.domain.Wallet;
import lombok.Getter;

@Getter
public class WalletResponse {

   private final Long id;
   private final Long userId;
   private final MoneyDTO balance;

   public WalletResponse(Wallet wallet) {
      this.id = wallet.getId();
      this.userId = wallet.getUserId();
      this.balance = wallet.getBalance().toDTO();
   }
}
