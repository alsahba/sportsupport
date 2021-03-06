package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.application.port.in.command.FindWalletActivityQuery;
import com.sport.support.wallet.domain.Wallet;
import com.sport.support.wallet.domain.WalletActivity;

import java.util.List;

public interface LoadWalletPort {

   Wallet loadByUserId(Long userId);

   List<WalletActivity> loadWalletActivities(FindWalletActivityQuery query);

}
