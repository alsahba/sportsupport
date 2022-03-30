package com.sport.support.membership.application.port.in.usecase;

import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.domain.Membership;

public interface AddMembershipUC {
   Membership add(AddMembershipCommand command);
}
