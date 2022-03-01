package com.sport.support.branch.controller.dto;

import com.sport.support.branch.entity.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDetailResponse {

    private String name;
    private String address;
    private PaymentDetailResponse paymentDetail;

    public BranchDetailResponse(Branch branch) {
        this.name = branch.getName();
        this.address = branch.getAddress();
        this.paymentDetail = new PaymentDetailResponse(branch.getPayment());
    }

}
