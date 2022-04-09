package com.sport.support.wallet.adapter.in.web;

import com.sport.support.shared.abstractions.adapters.web.AbstractController;
import com.sport.support.shared.common.web.DataResponse;
import com.sport.support.shared.common.web.Response;
import com.sport.support.wallet.adapter.in.web.payload.DepositMoneyRequest;
import com.sport.support.wallet.adapter.in.web.payload.FindWalletActivityRequest;
import com.sport.support.wallet.adapter.in.web.payload.WalletActivityResponse;
import com.sport.support.wallet.adapter.in.web.payload.WalletResponse;
import com.sport.support.wallet.application.port.in.command.ChangeBalanceCommand;
import com.sport.support.wallet.application.port.in.command.FindWalletActivityQuery;
import com.sport.support.wallet.application.port.in.usecase.DepositMoneyUC;
import com.sport.support.wallet.application.port.in.usecase.FindWalletUC;
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
   private final FindWalletUC findWalletUC;

   @PostMapping
   @PreAuthorize("hasAuthority('USER_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<WalletResponse> deposit(@Valid @RequestBody DepositMoneyRequest request,
                                           Principal principal) {
      var command = new ChangeBalanceCommand(getUserIdFromAuth(principal), request);
      var wallet = depositMoneyUC.deposit(command);
      return respond(new WalletResponse(wallet));
   }

   @GetMapping
   @PreAuthorize("hasAuthority('USER_READ')")
   @ResponseStatus(value = HttpStatus.OK)
   public Response<WalletResponse> getWallet(Principal principal) {
      var wallet = findWalletUC.findByUserId(getUserIdFromAuth(principal));
      return respond(new WalletResponse(wallet));
   }

   @PostMapping(value = "/activities")
   @PreAuthorize("hasAuthority('USER_READ')")
   @ResponseStatus(value = HttpStatus.OK)
   public Response<DataResponse<WalletActivityResponse>> getActivities(@Valid @RequestBody FindWalletActivityRequest request,
                                                              Principal principal) {
      var userId = getUserIdFromAuth(principal);
      var walletActivities = findWalletUC.findActivities(new FindWalletActivityQuery(userId, request));
      return respond(walletActivities.stream().map(WalletActivityResponse::new).toList());
   }
}