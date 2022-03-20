package com.sport.support.membership.controller.dto;

import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddMembershipRequest {

    @NotNull
    private Long branchId;

    @NotNull
    private Type type;

    private Duration duration;
}
