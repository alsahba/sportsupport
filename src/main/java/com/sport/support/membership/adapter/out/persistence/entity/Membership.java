package com.sport.support.membership.adapter.out.persistence.entity;


import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Status;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.domain.MembershipErrorMessages;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Membership extends AbstractAuditableEntity {

   @OneToOne
   @JoinColumn(name = "USER_ID", nullable = false)
   private AppUser user;

   @OneToOne
   @JoinColumn(name = "TRAINER_ID", nullable = false)
   private AppUser trainer;

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

   public Membership(AddMembershipCommand command) {
      setBranch(new Branch(command.getBranchId()));
      setDuration(command.getDuration());
      setStatus(Status.ACTIVE);
      setType(command.getType());
      setUser(new AppUser(command.getUserId()));
      setEndDate(calculateEndDate());
      setLoginAttempt(getType().getLoginAttempt());
   }

   private LocalDateTime calculateEndDate() {
      if (getDuration().equals(Duration.MONTHLY)) return LocalDateTime.now().plusMonths(1);
      else return LocalDateTime.now().plusYears(1);
   }

   public void cancel() {
      if (Status.CANCELLED.equals(getStatus())) {
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_ALREADY_CANCELLED);
      }
      setStatus(Status.CANCELLED);
   }
}
