package com.sport.support.wallet.adapter.in.web;

import com.sport.support.infrastructure.abstractions.adapters.web.AbstractRestController;
import com.sport.support.infrastructure.common.web.Response;
import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyRequest;
import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyResponse;
import com.sport.support.wallet.application.port.in.command.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import com.sport.support.wallet.domain.Wallet;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
class WalletController extends AbstractRestController {

   private final DepositMoneyUC depositMoneyUC;

   @PostMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<DepositMoneyResponse> deposit(@Valid @RequestBody DepositMoneyRequest request,
                                                 Principal principal) {
      var command = new DepositMoneyCommand(Long.valueOf(principal.getName()), request);
      Wallet wallet = depositMoneyUC.deposit(command);
      return respond(DepositMoneyResponse.success(wallet));
   }
}
