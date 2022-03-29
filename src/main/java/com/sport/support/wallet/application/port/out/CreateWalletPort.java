package com.sport.support.wallet.application.port.out;

import com.sport.support.wallet.domain.Wallet;

public interface CreateWalletPort {

   Wallet create(Long userId);

}
