package com.sport.support.wallet.application.port.out;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;

public interface DepositMoneyPort {

   void deposit(Wallet wallet, Money money);

}
