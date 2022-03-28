package com.sport.support.membership.application.service;

import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.membership.application.port.in.command.FindMembershipQuery;
import com.sport.support.membership.application.port.in.usecase.DoesMembershipExistUC;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DoesMembershipExistService implements DoesMembershipExistUC {

   private final DoesMembershipExistPort doesMembershipExistPort;

   @Override
   public boolean doesExist(FindMembershipQuery query) {
      return doesMembershipExistPort.doesExist(query.getUserId(), query.getTrainerId());
   }
}
