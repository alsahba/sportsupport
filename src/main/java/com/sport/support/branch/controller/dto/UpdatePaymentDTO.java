package com.sport.support.branch.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
public class UpdatePaymentDTO {

    private BigDecimal poolMembership;

    @NotNull
    private BigDecimal bronzeMembership;

    @NotNull
    private BigDecimal silverMembership;

    @NotNull
    private BigDecimal goldMembership;

    @NotNull
    private BigDecimal oneTimePass;
}
