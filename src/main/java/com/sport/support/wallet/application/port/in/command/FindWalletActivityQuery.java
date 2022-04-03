package com.sport.support.wallet.application.port.in.command;

import com.sport.support.shared.common.daterange.DateRange;
import com.sport.support.wallet.adapter.in.web.payload.FindWalletActivityRequest;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import lombok.Getter;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;

@Getter
public class FindWalletActivityQuery {

   private final Long userId;
   private final Collection<WalletActivityType> types;
   private final DateRange dateRange;

   public FindWalletActivityQuery(Long userId, FindWalletActivityRequest request) {
      this.userId = userId;
      if (request.getType() == null) {
         this.types = Arrays.stream(WalletActivityType.values()).toList();
      } else {
         this.types = Collections.singletonList(request.getType());
      }
      this.dateRange = request.getDateRange();
   }
}
