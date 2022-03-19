package com.sport.support.membership.entity;


import com.sport.support.appuser.entity.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.specification.BranchExistsSpecification;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.membership.controller.dto.AddMembershipRequest;
import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Status;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class
Membership extends AbstractAuditableEntity {

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

   @Enumerated(EnumType.STRING)
   private Status status;

   private int loginAttempt;

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
