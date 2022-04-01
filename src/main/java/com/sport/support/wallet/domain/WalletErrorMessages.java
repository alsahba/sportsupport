package com.sport.support.wallet.domain;

import com.sport.support.shared.exception.BusinessRuleErrorMessage;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum WalletErrorMessages implements BusinessRuleErrorMessage {

   ERROR_WALLET_INSUFFICIENT_BALANCE("SSEW-1","ERROR_WALLET_INSUFFICIENT_BALANCE"),
   ERROR_WALLET_NOT_FOUND("SSEW-2","ERROR_WALLET_NOT_FOUND");

   private final String code;
   private final String message;

}
