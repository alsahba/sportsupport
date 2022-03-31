package com.sport.support.membership.domain;

import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Status;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
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

   public Membership(Long id, Long userId, Long branchId, Long trainerId, Status status, Type type, Duration duration, int loginAttempt) {
      this.id = id;
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
      setStatus(Status.CANCELLED);
   }
}
