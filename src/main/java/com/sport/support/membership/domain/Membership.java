package com.sport.support.membership.domain;

import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Status;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class Membership {

   private Long id;
   private Long userId;
   private Long branchId;
   private Long trainerId;
   private Status status;
   private Type type;
   private Duration duration;
   private int loginAttempt;
   private LocalDateTime endDate;

   public Membership(AddMembershipCommand request) {
      setUserId(request.getUserId());
      setBranchId(request.getBranchId());
      setTrainerId(request.getTrainerId());
      setType(request.getType());
      setDuration(request.getDuration());
      setEndDate(calculateEndDate());
      setLoginAttempt(request.getType().getLoginAttempt());
      setStatus(Status.ACTIVE);
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
