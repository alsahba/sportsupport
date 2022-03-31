package com.sport.support.membership.adapter.in.web.payload;

import com.sport.support.membership.domain.Membership;
import lombok.Getter;

@Getter
public class MembershipResponse {

   private final Long id;
   private final Long userId;
   private final Long branchId;
   private final String type;
   private final String duration;

   public MembershipResponse(Membership membership) {
      this.id = membership.getId();
      this.userId = membership.getUserId();
      this.branchId = membership.getBranchId();
      this.type = membership.getType().toString();
      this.duration = membership.getDuration().toString();
   }
}
