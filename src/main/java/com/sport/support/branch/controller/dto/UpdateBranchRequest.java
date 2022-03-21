package com.sport.support.branch.controller.dto;

import com.sport.support.infrastructure.common.annotations.FixedPhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateBranchRequest {

    @NotNull
    @Min(1)
    private Long id;

    @NotBlank
    @Size(max = 64)
    private String name;

    @Min(1)
    private int quota;

    @NotBlank
    @Size(max = 256)
    private String address;

    @FixedPhoneNumber
    private String phoneNumber;

    @Valid
    private List<AddUpdatePaymentRequest> payment;

}
