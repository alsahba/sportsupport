package com.sport.support.membership.entity;

import com.sport.support.appuser.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MEMBERSHIP_HISTORY")
@Data
@NoArgsConstructor
public class MembershipHistory extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "USER_ID", nullable = false)
    private AppUser user;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @Column(name = "DURATION")
    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    public MembershipHistory(Membership membership) {
        setType(membership.getType());
        setBranch(membership.getBranch());
        setUser(membership.getUser());
        setDuration(membership.getDuration());
    }
}
