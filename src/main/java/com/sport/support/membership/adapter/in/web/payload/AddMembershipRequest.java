package com.sport.support.membership.adapter.in.web.payload;

import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddMembershipRequest {

    @NotNull
    @Positive
    private Long branchId;

    @NotNull
    @Positive
    private Long trainerId;

    @NotNull
    private Type type;

    @NotNull
    private Duration duration;

}
