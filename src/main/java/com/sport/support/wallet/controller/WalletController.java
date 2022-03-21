package com.sport.support.wallet.controller;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.wallet.controller.dto.TransferMoneyRequest;
import com.sport.support.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
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

   private final WalletService walletService;

   @PostMapping
   public ResponseEntity<String> transfer(@Valid @RequestBody TransferMoneyRequest request, Principal principal) {
      walletService.transfer(Long.valueOf(principal.getName()), new Money(request.getMoney()));
      return ResponseEntity.ok("Balance loaded");
   }
}
