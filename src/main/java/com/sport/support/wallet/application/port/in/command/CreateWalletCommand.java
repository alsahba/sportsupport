package com.sport.support.wallet.application.port.in.command;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
public class CreateWalletCommand {

   @NotNull
   private Long userId;

}
