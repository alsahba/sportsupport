package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.in.web.payload.AddBranchRequest;
import com.sport.support.branch.domain.Branch;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class AddBranchCommand {

   private final String name;
   private final int quota;
   private final Long cityId;
   private final Long districtId;
   private final String address;
   private final String phoneNumber;
   private final List<PaymentCommand> payments;

   public AddBranchCommand(AddBranchRequest request) {
      this.name = request.getName();
      this.quota = request.getQuota();
      this.cityId = request.getCityId();
      this.districtId = request.getDistrictId();
      this.address = request.getAddress();
      this.phoneNumber = request.getPhoneNumber();
      this.payments = request.getPayment().stream()
          .map(PaymentCommand::new)
          .collect(Collectors.toList());
   }

   public Branch toDomain() {
      return Branch.builder()
          .name(name)
          .quota(quota)
          .cityId(cityId)
          .districtId(districtId)
          .address(address)
          .phoneNumber(phoneNumber)
          .payments(payments.stream().map(PaymentCommand::toDomain).collect(Collectors.toSet()))
          .build();
   }
}
