package com.sport.support.membership.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.MembershipErrorMessages;
import com.sport.support.membership.domain.enumeration.Status;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.membership.domain.vo.MembershipId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@SuperBuilder
public class Membership extends AbstractDomainObject<MembershipId> {

   private UserId userId;
   private BranchId branchId;
   private EmployeeId trainerId;
   private Status status;
   private Type type;
   private Duration duration;
   private int loginAttempt;
   private LocalDateTime endDate;

   public Membership(Long id, UserId userId, BranchId branchId, EmployeeId trainerId, Status status, Type type, Duration duration, int loginAttempt) {
      super(new MembershipId(id));
      this.userId = userId;
      this.branchId = branchId;
      this.trainerId = trainerId;
      this.status = status;
      this.type = type;
      this.duration = duration;
      this.loginAttempt = loginAttempt;
      this.endDate = calculateEndDate();
   }

   private LocalDateTime calculateEndDate() {
      if (getDuration().equals(Duration.MONTHLY)) return LocalDateTime.now().plusMonths(1);
      else return LocalDateTime.now().plusYears(1);
   }

   public void cancel() {
      if (Status.CANCELLED.equals(getStatus())) {
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_ALREADY_CANCELLED);
      }
      this.status = Status.CANCELLED;
   }
}
