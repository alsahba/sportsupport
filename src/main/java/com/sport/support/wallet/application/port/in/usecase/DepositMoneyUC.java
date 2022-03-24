package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.DepositMoneyCommand;

public interface DepositMoneyUC {
   void deposit(DepositMoneyCommand command);
}
