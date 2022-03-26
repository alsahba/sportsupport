package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.in.web.payload.UpdateBranchRequest;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UpdateBranchCommand {

   private final Long id;
   private final String name;
   private final int quota;
   private final String address;
   private final String phoneNumber;
   private final List<PaymentCommand> payments;

   public UpdateBranchCommand(UpdateBranchRequest request) {
      this.id = request.getId();
      this.name = request.getName();
      this.quota = request.getQuota();
      this.address = request.getAddress();
      this.phoneNumber = request.getPhoneNumber();
      this.payments = request.getPayment().stream().map(PaymentCommand::new).collect(Collectors.toList());
   }
}