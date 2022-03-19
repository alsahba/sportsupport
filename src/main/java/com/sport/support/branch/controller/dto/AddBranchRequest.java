package com.sport.support.branch.controller.dto;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
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
    private List<AddUpdatePaymentRequest> payment;

}
