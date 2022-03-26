package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
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
