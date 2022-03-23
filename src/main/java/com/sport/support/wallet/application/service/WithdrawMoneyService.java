package com.sport.support.wallet.application.service;

import com.sport.support.wallet.application.port.in.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.WithdrawMoneyUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.WithdrawMoneyPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class WithdrawMoneyService implements WithdrawMoneyUC {

   private final LoadWalletPort loadWalletPort;
   private final WithdrawMoneyPort withdrawMoneyPort;

   @Override
   public void withdraw(WithdrawMoneyCommand command) {
      var wallet = loadWalletPort.load(command.getUserId());
      wallet.withdraw(command.getMoney());
      withdrawMoneyPort.withdraw(wallet, command.getMoney().negate());
   }
}
