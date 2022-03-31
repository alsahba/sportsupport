package com.sport.support.wallet.adapter.in.listener;

import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class WalletEventListener implements ApplicationListener<WithdrawMoneyCommand> {

   private final WithdrawMoneyUC withdrawMoneyUC;

   @Override
   public void onApplicationEvent(@NonNull WithdrawMoneyCommand command) {
      withdrawMoneyUC.withdraw(command);
   }
}
