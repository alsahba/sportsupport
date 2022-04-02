package com.sport.support.wallet.application.service;

import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.wallet.application.port.in.command.FindWalletActivityQuery;
import com.sport.support.wallet.application.port.in.usecase.FindWalletUC;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.domain.Wallet;
import com.sport.support.wallet.domain.WalletActivity;
import lombok.RequiredArgsConstructor;

import java.util.List;

@UseCase
@RequiredArgsConstructor
public class FindWalletService implements FindWalletUC {

   private final LoadWalletPort loadWalletPort;

   @Override
   public Wallet findByUserId(Long id) {
      return loadWalletPort.loadByUserId(id);
   }

   @Override
   public List<WalletActivity> findActivities(FindWalletActivityQuery query) {
      return loadWalletPort.loadWalletActivities(query);
   }
}
