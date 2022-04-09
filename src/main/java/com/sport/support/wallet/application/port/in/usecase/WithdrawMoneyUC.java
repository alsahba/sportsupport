package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;

public interface WithdrawMoneyUC {

    void withdraw(ChangeBalanceCommand command);

}
