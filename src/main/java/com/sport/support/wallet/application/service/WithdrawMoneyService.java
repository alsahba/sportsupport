package com.sport.support.wallet.application.service;

import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class WithdrawMoneyService implements WithdrawMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final UpdateWalletBalancePort withdrawPort;

   @Override
   public void withdraw(WithdrawMoneyCommand command) {
      var wallet = loadWalletPort.loadByUserId(command.getUserId());
      wallet.withdraw(command.getMoney());
      withdrawPort.update(wallet);
   }
}
