package com.sport.support.membership.entity;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class MembershipHistory extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private Type type;

    public MembershipHistory(Membership membership) {
        setType(membership.getType());
        setBranch(membership.getBranch());
        setUser(membership.getUser());
        setDuration(membership.getDuration());
    }
}
