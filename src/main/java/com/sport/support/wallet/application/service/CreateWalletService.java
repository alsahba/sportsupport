package com.sport.support.wallet.application.service;

import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.CreateWalletCommand;
import com.sport.support.wallet.application.port.in.usecase.CreateWalletUC;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
class CreateWalletService implements CreateWalletUC {

   private final CreateWalletPort createWalletPort;

   @Override
   public void create(CreateWalletCommand command) {
      createWalletPort.create(command.getUserId());
   }
}
