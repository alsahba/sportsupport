package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.infrastructure.common.money.MoneyDTO;
import com.sport.support.wallet.domain.Wallet;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class DepositMoneyResponse {

   private String message;
   private Long id;
   private Long userId;
   private MoneyDTO balance;

   public static DepositMoneyResponse success(Wallet wallet) {
      return DepositMoneyResponse.builder()
          .message("Deposit money successfully")
          .id(wallet.getId())
          .userId(wallet.getUserId())
          .balance(wallet.getBalance().toDTO())
          .build();
   }
}
