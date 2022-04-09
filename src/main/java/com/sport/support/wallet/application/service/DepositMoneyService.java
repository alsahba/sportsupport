package com.sport.support.wallet.application.service;

import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import com.sport.support.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class DepositMoneyService implements DepositMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final UpdateWalletBalancePort depositPort;

   @Override
   public Wallet deposit(ChangeBalanceCommand command) {
      var wallet = loadWalletPort.loadByUserId(command.getUserId());
      wallet.deposit(command.getMoney());
      depositPort.update(wallet);
      return wallet;
   }
}
