package com.sport.support.branch.entity;

import com.sport.support.branch.controller.dto.AddUpdatePaymentRequest;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Data
@NoArgsConstructor
@Table(name = "PAYMENT")
@EqualsAndHashCode(callSuper = true)
public class Payment extends AbstractAuditableEntity {

    @Column(name = "POOL_MEMBERSHIP", precision = 19)
    private BigDecimal poolMembership;

    @Column(name = "BRONZE_MEMBERSHIP", precision = 19)
    private BigDecimal bronzeMembership;

    @Column(name = "SILVER_MEMBERSHIP", precision = 19)
    private BigDecimal silverMembership;

    @Column(name = "GOLD_MEMBERSHIP", precision = 19)
    private BigDecimal goldMembership;

    @Column(name = "ONE_TIME_PASS", precision = 19)
    private BigDecimal oneTimePass;

    public Payment(AddUpdatePaymentRequest addUpdatePaymentRequest) {
        this.poolMembership = addUpdatePaymentRequest.getPoolMembership();
        this.bronzeMembership = addUpdatePaymentRequest.getBronzeMembership();
        this.silverMembership = addUpdatePaymentRequest.getSilverMembership();
        this.goldMembership = addUpdatePaymentRequest.getGoldMembership();
        this.oneTimePass = addUpdatePaymentRequest.getOneTimePass();
    }
}
