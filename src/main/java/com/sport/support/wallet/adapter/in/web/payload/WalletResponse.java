package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.infrastructure.common.money.MoneyDTO;
import com.sport.support.wallet.domain.Wallet;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class WalletResponse {

   private Long id;
   private Long userId;
   private MoneyDTO balance;

   public static WalletResponse success(Wallet wallet) {
      return WalletResponse.builder()
          .id(wallet.getId())
          .userId(wallet.getUserId())
          .balance(wallet.getBalance().toDTO())
          .build();
   }
}
