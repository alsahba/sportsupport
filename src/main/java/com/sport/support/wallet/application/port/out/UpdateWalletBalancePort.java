package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;

public interface UpdateWalletBalancePort {
   void update(Wallet wallet);
}
