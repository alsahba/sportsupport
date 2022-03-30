package com.sport.support.membership.adapter.in.web.payload;

import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import com.sport.support.membership.domain.Membership;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class MembershipResponse {

   private final Long id;
   private final Long userId;
   private final Long branchId;
   private final Type type;
   private final Duration duration;

   public static MembershipResponse success(Membership membership) {
      return MembershipResponse.builder()
          .id(membership.getId())
          .userId(membership.getUserId())
          .branchId(membership.getBranchId())
          .type(membership.getType())
          .duration(membership.getDuration())
          .build();
   }
}
