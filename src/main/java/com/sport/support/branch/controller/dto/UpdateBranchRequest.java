package com.sport.support.branch.controller.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class UpdateBranchRequest {

    @NotNull
    private Long id;

    @NotBlank
    private String name;

    @Min(1)
    private int quota;

    @NotBlank
    private String address;

    @Valid
    private AddUpdatePaymentRequest payment;

}
