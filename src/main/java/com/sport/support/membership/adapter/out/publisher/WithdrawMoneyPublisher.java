package com.sport.support.membership.adapter.out.publisher;

import com.sport.support.shared.common.money.Money;
import com.sport.support.membership.application.port.out.PublishWithdrawMoneyPort;
import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WithdrawMoneyPublisher implements PublishWithdrawMoneyPort {

   private final ApplicationEventPublisher publisher;

   @Override
   public void publishWithdrawMoney(Long userId, Money money) {
      publisher.publishEvent(new WithdrawMoneyCommand(this, userId, money));
   }
}
