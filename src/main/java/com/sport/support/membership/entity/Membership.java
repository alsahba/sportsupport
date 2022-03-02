package com.sport.support.membership.entity;


import com.sport.support.appuser.entity.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.specification.BranchExistsSpecification;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.controller.dto.AddMembershipRequest;
import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MEMBERSHIP")
@Data
@NoArgsConstructor
public class Membership extends AbstractAuditableEntity {

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

    @Column(name = "LOGIN_ATTEMPT")
    private int loginAttempt;

    @Column(name = "END_DATE")
    private LocalDateTime endDate;

    public Membership(Long userId, AddMembershipRequest addRequest) {
        setBranch(new Branch(addRequest.getBranchId()));
        setDuration(addRequest.getDuration());
        setType(addRequest.getType());
        setUser(new AppUser(userId));
        setEndDate(calculateEndDate());
        setLoginAttempt(getType().getLoginAttempt());
    }

    private LocalDateTime calculateEndDate() {
        if (getDuration().equals(Duration.MONTHLY)) return LocalDateTime.now().plusMonths(1);
        else return LocalDateTime.now().plusYears(1);
    }

    public boolean isAddable() {
        return isBranchExists(); //TODO has wallet sufficient amount?
    }

    private boolean isBranchExists() {
        return new BranchExistsSpecification().isSatisfiedBy(getBranch());
    }

}
