package com.sport.support.wallet.adapter.in.web.payload;

import com.sport.support.infrastructure.common.MoneyDTO;
import lombok.Data;

@Data
public class DepositMoneyRequest {

   private MoneyDTO money;

}
