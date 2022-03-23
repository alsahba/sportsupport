package com.sport.support.wallet.application.service;

import com.sport.support.wallet.application.port.in.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.DepositMoneyUC;
import com.sport.support.wallet.application.port.out.DepositMoneyPort;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DepositMoneyService implements DepositMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final DepositMoneyPort depositMoneyPort;

   @Override
   public void deposit(DepositMoneyCommand command) {
      var wallet = loadWalletPort.load(command.getUserId());
      wallet.deposit(command.getMoney());
      depositMoneyPort.deposit(wallet, command.getMoney());
   }
}
