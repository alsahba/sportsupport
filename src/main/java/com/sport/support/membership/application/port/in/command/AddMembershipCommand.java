package com.sport.support.membership.application.port.in.command;

import com.sport.support.membership.adapter.in.web.payload.AddMembershipRequest;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import lombok.Getter;

@Getter
public class AddMembershipCommand {

   private final Long userId;
   private final Long branchId;
   private final Type type;
   private final Duration duration;

   public AddMembershipCommand(Long userId, AddMembershipRequest request) {
      this.userId = userId;
      this.branchId = request.getBranchId();
      this.type = request.getType();
      this.duration = request.getDuration();
   }
}
