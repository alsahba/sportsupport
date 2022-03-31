package com.sport.support.wallet.application.port.in.command;

import com.sport.support.infrastructure.common.money.Money;
import lombok.Getter;
import org.springframework.context.ApplicationEvent;

@Getter
public class WithdrawMoneyCommand extends ApplicationEvent {

   private final Long userId;
   private final Money money;

   public WithdrawMoneyCommand(Object source, Long userId, Money money) {
      super(source);
      this.userId = userId;
      this.money = money;
   }
}
