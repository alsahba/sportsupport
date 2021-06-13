package com.sport.support.branch.controller.dto;

import com.sport.support.branch.entity.Payment;
import com.sport.support.infrastructure.Util;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PaymentDetailDTO {

    private String poolMembership;
    private String bronzeMembership;
    private String silverMembership;
    private String goldMembership;
    private String oneTimePass;

    public PaymentDetailDTO(Payment payment) {
        if (payment.getPoolMembership() != null) {
            // TODO: 5/19/2021 will be refactored!
            this.poolMembership = Util.convertToFormattedMoney(payment.getPoolMembership());
        }
        this.bronzeMembership = Util.convertToFormattedMoney(payment.getBronzeMembership());
        this.silverMembership = Util.convertToFormattedMoney(payment.getSilverMembership());
        this.goldMembership = Util.convertToFormattedMoney(payment.getGoldMembership());
        this.oneTimePass = Util.convertToFormattedMoney(payment.getOneTimePass());
    }
}
