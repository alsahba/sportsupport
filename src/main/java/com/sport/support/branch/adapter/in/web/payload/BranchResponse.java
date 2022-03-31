package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.branch.domain.Branch;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class BranchResponse {

    private final String name;
    private final String address;
    private final Set<PaymentResponse> paymentDetail;

    public BranchResponse(Branch branch) {
        this.name = branch.getName();
        this.address = branch.getAddress();
        this.paymentDetail = branch.getPayments().stream().map(PaymentResponse::new).collect(Collectors.toSet());
    }
}
