package com.sport.support.member.controller.dto;

import com.sport.support.member.entity.enumeration.Duration;
import com.sport.support.member.entity.enumeration.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddMembershipDTO {

    @NotNull
    private Long branchId;

    @NotNull
    private Type type;

    // TODO: 5/19/2021 Nullable if type is one time pass
    private Duration duration;
}
