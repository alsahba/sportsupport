package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;
import com.sport.support.wallet.domain.Wallet;

public interface DepositMoneyUC {
   Wallet deposit(ChangeBalanceCommand command);
}
