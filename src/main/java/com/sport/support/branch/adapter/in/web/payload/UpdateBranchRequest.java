package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.infrastructure.common.annotations.validation.FixedPhoneNumber;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.*;
import java.util.List;

@Data
@NoArgsConstructor
public class UpdateBranchRequest {

    @NotNull
    @Positive
    private Long id;

    @NotBlank
    @Size(max = 64)
    private String name;

    @Positive
    private int quota;

    @NotBlank
    @Size(max = 256)
    private String address;

    @FixedPhoneNumber
    private String phoneNumber;

    @Valid
    private List<AddUpdatePaymentRequest> payment;

}
