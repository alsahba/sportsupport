package com.sport.support.wallet.application.port.in;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.wallet.adapter.in.web.payload.TransferMoneyRequest;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
public class DepositMoneyCommand {

   @NotNull
   private final Long userId;

   @Valid
   private final Money money;

   public DepositMoneyCommand(Long userId, TransferMoneyRequest request) {
      this.userId = userId;
      this.money = new Money(request.getMoney());
   }
}
