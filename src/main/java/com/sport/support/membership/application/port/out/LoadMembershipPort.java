package com.sport.support.membership.application.port.out;

import com.sport.support.membership.adapter.out.persistence.entity.Membership;

public interface LoadMembershipPort {
   Membership loadByUserId(Long userId);
}
