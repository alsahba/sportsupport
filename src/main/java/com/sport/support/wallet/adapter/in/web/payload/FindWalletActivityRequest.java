package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.shared.common.daterange.DateRange;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class FindWalletActivityRequest {

   private WalletActivityType type;

   @NotNull
   private DateRange dateRange;

}
