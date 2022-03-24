package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.infrastructure.common.annotations.validation.FixedPhoneNumber;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
public class AddBranchRequest {

    @NotBlank
    @Size(max = 64)
    private String name;

    @Min(1)
    private int quota;

    @NotNull
    @Min(1)
    private Long cityId;

    @NotNull
    @Min(1)
    private Long districtId;

    @NotBlank
    @Size(max = 256)
    private String address;

    @FixedPhoneNumber
    private String phoneNumber;

    @Valid
    private List<AddUpdatePaymentRequest> payment;

}