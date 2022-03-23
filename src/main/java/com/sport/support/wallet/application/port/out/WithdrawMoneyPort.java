package com.sport.support.wallet.application.port.out;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.wallet.adapter.out.persistence.Wallet;

public interface WithdrawMoneyPort {

   void withdraw(Wallet wallet, Money money);

}
