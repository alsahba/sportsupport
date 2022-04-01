package com.sport.support.membership.application.port.out;

import com.sport.support.shared.common.money.Money;

public interface PublishWithdrawMoneyPort {

    void publishWithdrawMoney(Long userId, Money money);

}
