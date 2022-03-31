package com.sport.support.membership.application.port.in.command;

import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Status;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import com.sport.support.membership.domain.Membership;
import lombok.Getter;

@Getter
public class AddMembershipCommand {

   private final Long userId;
   private final Long trainerId;
   private final Long branchId;
   private final Type type;
   private final Duration duration;

   public AddMembershipCommand(Long userId, AddMembershipRequest request) {
      this.userId = userId;
      this.trainerId = request.getTrainerId();
      this.branchId = request.getBranchId();
      this.type = request.getType();
      this.duration = request.getDuration();
   }

   public Membership toDomain() {
      return Membership.builder()
          .userId(userId)
          .trainerId(trainerId)
          .branchId(branchId)
          .status(Status.ACTIVE)
          .type(type)
          .duration(duration)
          .build();
   }
}
