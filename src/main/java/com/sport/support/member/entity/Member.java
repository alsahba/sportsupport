package com.sport.support.member.entity;


import com.sport.support.appuser.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.specification.BranchExistsSpecification;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.member.entity.enumeration.Duration;
import com.sport.support.member.entity.enumeration.Type;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBER")
@Data
@NoArgsConstructor
public class Member extends AbstractAuditableEntity {

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

    public boolean isAddable() {
        return isBranchExists(); //TODO has wallet sufficient amount?
    }

    private boolean isBranchExists() {
        return new BranchExistsSpecification().isSatisfiedBy(getBranch());
    }

}
