package com.sport.support.wallet.application.service;

import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class DepositMoneyService implements DepositMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final UpdateWalletBalancePort depositPort;

   // TODO: 28.03.2022 wallet activity development

   @Override
   public void deposit(DepositMoneyCommand command) {
      var wallet = loadWalletPort.load(command.getUserId());
      wallet.deposit(command.getMoney());
      depositPort.update(wallet);
   }
}
