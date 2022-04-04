package com.sport.support.wallet.domain;

import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import com.sport.support.wallet.domain.vo.WalletActivityId;
import com.sport.support.wallet.domain.vo.WalletId;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class WalletActivity extends AbstractDomainObject<WalletActivityId> {

   private WalletId walletId;
   private WalletActivityType type;
   private Money amount;
   private LocalDateTime transactionDate;

}
