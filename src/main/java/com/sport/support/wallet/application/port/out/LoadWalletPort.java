package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.domain.Wallet;

public interface LoadWalletPort {

   Wallet load(Long userId);

}
