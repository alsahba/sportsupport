package com.sport.support.branch.controller.dto;

import com.sport.support.branch.entity.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class BranchDetailResponse {

    private String name;
    private String address;
    private Set<PaymentDetailResponse> paymentDetail;

    public BranchDetailResponse(Branch branch) {
        this.name = branch.getName();
        this.address = branch.getAddress();
        this.paymentDetail = branch.getPayments().stream()
            .map(PaymentDetailResponse::new).collect(Collectors.toSet());
    }
}
