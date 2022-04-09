package com.sport.support.wallet.application.port.in.command;

import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyRequest;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangeBalanceCommand {

   private Long userId;
   private Money money;
   private boolean negate;

   public ChangeBalanceCommand(Long userId, DepositMoneyRequest request) {
      this.userId = userId;
      this.money = new Money(request.getMoney());
      this.negate = false;
   }

}
