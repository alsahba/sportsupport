package com.sport.support.wallet.adapter.in.web;

import com.sport.support.infrastructure.abstractions.adapters.web.AbstractController;
import com.sport.support.infrastructure.common.web.Response;
import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyRequest;
import com.sport.support.wallet.adapter.in.web.payload.WalletResponse;
import com.sport.support.wallet.application.port.in.command.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
class WalletController extends AbstractController {

   private final DepositMoneyUC depositMoneyUC;

   @PostMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<WalletResponse> deposit(@Valid @RequestBody DepositMoneyRequest request,
                                           Principal principal) {
      var command = new DepositMoneyCommand(Long.valueOf(principal.getName()), request);
      var wallet = depositMoneyUC.deposit(command);
      return respond(new WalletResponse(wallet));
   }
}