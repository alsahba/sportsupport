package com.sport.support.membership.application.port.in.usecase;

import com.sport.support.membership.application.port.in.command.FindMembershipQuery;

public interface DoesMembershipExistUC {
   boolean doesExist(FindMembershipQuery query);
}