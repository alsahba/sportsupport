package com.sport.support.membership.application.port.out;

import com.sport.support.membership.domain.Membership;

public interface LoadMembershipPort {
   Membership loadByUserId(Long userId);
}
