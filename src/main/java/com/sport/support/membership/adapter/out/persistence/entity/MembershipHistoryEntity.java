package com.sport.support.membership.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERSHIP_HISTORY")
@Getter
@Setter
@NoArgsConstructor
public class MembershipHistoryEntity extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "MEMBERSHIP_ID", nullable = false)
    private MembershipEntity membership;

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

    public MembershipHistoryEntity(MembershipEntity membershipEntity) {
        setMembership(membershipEntity);
        setType(membershipEntity.getType());
        setBranch(membershipEntity.getBranch());
        setUser(membershipEntity.getUser());
        setDuration(membershipEntity.getDuration());
    }
}
