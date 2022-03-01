package com.sport.support.branch.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddBranchRequest {

    @NotBlank
    private String name;

    @Min(1)
    private int quota;

    @NotNull
    private Long cityId;

    @NotNull
    private Long districtId;

    @NotBlank
    private String address;

    @Valid
    private AddUpdatePaymentRequest payment;
}
