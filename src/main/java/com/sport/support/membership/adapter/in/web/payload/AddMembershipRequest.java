package com.sport.support.membership.adapter.in.web.payload;

import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
public class AddMembershipRequest {

    @NotNull
    private Long branchId;

    @NotNull
    private Long trainerId;

    @NotNull
    private Type type;

    private Duration duration;
}
