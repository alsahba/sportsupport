package com.sport.support.wallet.application.service;

import com.sport.support.wallet.adapter.out.persistence.Wallet;
import com.sport.support.wallet.application.port.in.CreateWalletUC;
import com.sport.support.wallet.application.port.in.CreateWalletCommand;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CreateWalletService implements CreateWalletUC {

   private final CreateWalletPort createWalletPort;

   @Override
   public void create(CreateWalletCommand command) {
      Wallet wallet = new Wallet(command.getUserId());
      createWalletPort.create(wallet);
   }
}
