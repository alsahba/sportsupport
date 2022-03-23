package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.adapter.out.persistence.Wallet;

public interface CreateWalletPort {

   void create(Wallet wallet);

}
