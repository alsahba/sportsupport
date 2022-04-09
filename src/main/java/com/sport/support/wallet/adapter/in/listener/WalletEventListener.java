package com.sport.support.wallet.adapter.in.listener;

import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletEventListener {

   private final WithdrawMoneyUC withdrawMoneyUC;
   private final DepositMoneyUC depositMoneyUC;

   @KafkaListener(topics = "change-balance-topic", groupId = "wallet")
   public void listen(ChangeBalanceCommand command) {
      if (command.isNegate()) {
         withdrawMoneyUC.withdraw(command);
      } else {
         depositMoneyUC.deposit(command);
      }
   }
}
