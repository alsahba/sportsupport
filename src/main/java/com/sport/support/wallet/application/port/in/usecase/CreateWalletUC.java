package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.CreateWalletCommand;

public interface CreateWalletUC {
    void create(CreateWalletCommand command);
}
