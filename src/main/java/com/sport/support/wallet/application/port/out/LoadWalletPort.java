package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.adapter.out.persistence.Wallet;

public interface LoadWalletPort {

   Wallet load(Long userId);

}
