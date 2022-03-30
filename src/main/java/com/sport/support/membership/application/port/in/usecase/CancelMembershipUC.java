package com.sport.support.membership.application.port.in.usecase;

import com.sport.support.membership.domain.Membership;

public interface CancelMembershipUC {
   Membership cancel(Long userId);
}
