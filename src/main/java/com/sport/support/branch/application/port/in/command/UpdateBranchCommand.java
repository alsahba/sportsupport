package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.in.web.payload.UpdateBranchRequest;
import com.sport.support.branch.domain.Branch;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.branch.domain.vo.CityId;
import com.sport.support.branch.domain.vo.DistrictId;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class UpdateBranchCommand {

   private final Long userId;
   private final Long id;
   private final String name;
   private final int quota;
   private final String address;
   private final String phoneNumber;
   private final List<PaymentCommand> payments;

   public UpdateBranchCommand(Long userId, UpdateBranchRequest request) {
      this.userId = userId;
      this.id = request.getId();
      this.name = request.getName();
      this.quota = request.getQuota();
      this.address = request.getAddress();
      this.phoneNumber = request.getPhoneNumber();
      this.payments = request.getPayment().stream().map(PaymentCommand::new).collect(Collectors.toList());
   }

   public Branch toDomain() {
      return Branch.builder()
          .idVO(new BranchId(id))
          .name(name)
          .quota(quota)
          .cityId(new CityId(null))
          .districtId(new DistrictId(null))
          .address(address)
          .phoneNumber(phoneNumber)
          .payments(payments.stream().map(PaymentCommand::toDomain).collect(Collectors.toSet()))
          .build();
   }
}
