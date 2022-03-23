package com.sport.support.wallet.adapter.in.web;

import com.sport.support.wallet.adapter.in.web.payload.TransferMoneyRequest;
import com.sport.support.wallet.application.port.in.DepositMoneyCommand;
import com.sport.support.wallet.application.port.in.DepositMoneyUC;
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
public class WalletController {

   private final DepositMoneyUC depositMoneyUC;

   @PostMapping
   @PreAuthorize("hasRole('USER_WRITE')")
   public ResponseEntity<String> transfer(@Valid @RequestBody TransferMoneyRequest request, Principal principal) {
      depositMoneyUC.deposit(new DepositMoneyCommand(Long.valueOf(principal.getName()), request));
      return ResponseEntity.ok("Balance loaded");
   }
}
