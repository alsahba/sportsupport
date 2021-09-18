package com.sport.support.member.entity;


import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.specification.BranchExistsSpecification;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.enumeration.Status;
import com.sport.support.member.controller.dto.AddMembershipDTO;
import com.sport.support.member.entity.enumeration.Duration;
import com.sport.support.member.entity.enumeration.Type;
import com.sport.support.member.specifications.MemberExistsSpecification;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "MEMBERSHIP")
@Data
@NoArgsConstructor
public class Membership extends AbstractAuditableEntity {

    @ManyToOne
    @JoinColumn(name = "MEMBER_ID", nullable = false)
    private Member member;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", nullable = false)
    private Branch branch;

    @Column(name = "STATUS")
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "DURATION")
    @Enumerated(EnumType.STRING)
    private Duration duration;

    @Column(name = "TYPE")
    @Enumerated(EnumType.STRING)
    private Type type;

    @Column(name = "LOGIN_ATTEMPT")
    private int loginAttempt;

    public Membership(AddMembershipDTO addMembershipDTO, Long memberId) {
        this.branch = new Branch(addMembershipDTO.getBranchId());
        this.status = Status.ACTIVE;
        this.duration = addMembershipDTO.getDuration();
        this.type = addMembershipDTO.getType();
        this.loginAttempt = getType().getLoginAttempt();
        this.member = new Member(memberId);
    }

    public boolean isAddable() {
        return isBranchExists() && isMemberExists(); //TODO has wallet sufficient amount?
    }

    private boolean isBranchExists() {
        return new BranchExistsSpecification().isSatisfiedBy(getBranch());
    }

    private boolean isMemberExists() {
        return new MemberExistsSpecification().isSatisfiedBy(getMember());
    }
}
