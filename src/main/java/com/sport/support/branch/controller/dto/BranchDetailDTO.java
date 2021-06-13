package com.sport.support.branch.controller.dto;

import com.sport.support.branch.entity.Branch;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class BranchDetailDTO {

    private String name;
    private String address;
    private PaymentDetailDTO paymentDetail;

    public BranchDetailDTO(Branch branch) {
        this.name = branch.getName();
        this.address = branch.getAddress();
        this.paymentDetail = new PaymentDetailDTO(branch.getPayment());
    }

}
