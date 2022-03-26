package com.sport.support.wallet.application.service;

import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;
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
      var wallet = new Wallet(command.getUserId());
      createWalletPort.create(wallet);
   }
}
