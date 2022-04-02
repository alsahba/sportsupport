package com.sport.support.wallet.application.port.in.usecase;

import com.sport.support.wallet.application.port.in.command.FindWalletActivityQuery;
import com.sport.support.wallet.domain.Wallet;
import com.sport.support.wallet.domain.WalletActivity;

import java.util.List;

public interface FindWalletUC {

   Wallet findByUserId(Long id);

   List<WalletActivity> findActivities(FindWalletActivityQuery query);

}
