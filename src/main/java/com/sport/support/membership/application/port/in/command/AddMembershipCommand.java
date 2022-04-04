package com.sport.support.membership.application.port.in.command;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Status;
import com.sport.support.membership.domain.enumeration.Type;
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
          .userId(new UserId(userId))
          .trainerId(new EmployeeId(trainerId))
          .branchId(new BranchId(branchId))
          .status(Status.ACTIVE)
          .type(type)
          .duration(duration)
          .build();
   }
}
