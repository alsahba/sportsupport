package com.sport.support.membership.application.port.in.usecase;

import com.sport.support.membership.application.port.in.command.AddMembershipCommand;

public interface AddMembershipUC {
   void add(AddMembershipCommand command);
}
