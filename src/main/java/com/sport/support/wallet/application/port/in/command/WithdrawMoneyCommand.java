package com.sport.support.wallet.application.port.in.command;

import com.sport.support.infrastructure.common.money.Money;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class WithdrawMoneyCommand {

   @NotNull
   private final Long userId;

   @Valid
   private final Money money;

}
