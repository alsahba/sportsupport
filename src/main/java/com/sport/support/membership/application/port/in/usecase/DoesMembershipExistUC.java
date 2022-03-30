package com.sport.support.membership.application.port.in.usecase;

import com.sport.support.membership.application.port.in.command.FindMembershipQuery;

public interface DoesMembershipExistUC {

   boolean doesExistByUserAndTrainer(FindMembershipQuery query);

   boolean doesExistByUser(FindMembershipQuery query);

}