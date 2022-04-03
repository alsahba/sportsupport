package com.sport.support.membership.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "MEMBERSHIP_HISTORY")
@NoArgsConstructor
public class MembershipHistoryEntity extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(nullable = false)
    private MembershipEntity membership;

    @ManyToOne
    @JoinColumn(nullable = false)
    private AppUserEntity user;

    @ManyToOne
    @JoinColumn(nullable = false)
    private BranchEntity branchEntity;

    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Enumerated(EnumType.STRING)
    private Type type;

    public MembershipHistoryEntity(MembershipEntity membershipEntity) {
        setMembership(membershipEntity);
        setType(membershipEntity.getType());
        setBranchEntity(membershipEntity.getBranchEntity());
        setUser(membershipEntity.getUser());
        setDuration(membershipEntity.getDuration());
    }
}
