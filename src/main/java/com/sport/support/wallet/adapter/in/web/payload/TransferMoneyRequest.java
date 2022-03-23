package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.infrastructure.common.MoneyDTO;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TransferMoneyRequest {

   private MoneyDTO money;
}
