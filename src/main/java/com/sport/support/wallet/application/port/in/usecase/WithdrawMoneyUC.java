package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;

public interface WithdrawMoneyUC {
    void withdraw(WithdrawMoneyCommand command);
}
