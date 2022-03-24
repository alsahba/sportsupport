package com.sport.support.wallet.application.service;

import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.WithdrawMoneyPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class WithdrawMoneyService implements WithdrawMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final WithdrawMoneyPort withdrawMoneyPort;

   @Override
   public void withdraw(WithdrawMoneyCommand command) {
      var wallet = loadWalletPort.load(command.getUserId());
      wallet.withdraw(command.getMoney());
      withdrawMoneyPort.withdraw(wallet, command.getMoney().negate());
   }
}
