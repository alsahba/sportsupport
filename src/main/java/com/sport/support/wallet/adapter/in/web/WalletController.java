package com.sport.support.wallet.adapter.in.web;

import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyRequest;
import com.sport.support.wallet.application.port.in.command.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.security.Principal;

@RestController
@RequestMapping("/wallets")
@RequiredArgsConstructor
class WalletController {

   private final DepositMoneyUC depositMoneyUC;

   @PostMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   public ResponseEntity<String> deposit(@Valid @RequestBody DepositMoneyRequest request, Principal principal) {
      depositMoneyUC.deposit(new DepositMoneyCommand(Long.valueOf(principal.getName()), request));
      return ResponseEntity.ok("Money deposited");
   }
}
