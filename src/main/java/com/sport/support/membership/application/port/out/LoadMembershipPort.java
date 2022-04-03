package com.sport.support.membership.application.port.out;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.membership.domain.Membership;

public interface LoadMembershipPort {
   Membership loadByUserId(UserId userId);
}
