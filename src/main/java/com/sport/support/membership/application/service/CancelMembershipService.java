package com.sport.support.membership.application.service;

import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.usecase.ReleaseQuotaUC;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.membership.application.port.in.usecase.CancelMembershipUC;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.Membership;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CancelMembershipService implements CancelMembershipUC {

   private final ReleaseQuotaUC releaseQuotaUC;
   private final SaveMembershipPort saveMembershipPort;
   private final LoadMembershipPort loadMembershipPort;

   @Override
   public Membership cancel(Long userId) {
      Membership membership = loadMembershipPort.loadByUserId(userId);
      membership.cancel();
      releaseQuotaUC.releaseQuota(new BranchMembershipCommand(membership.getBranchId()));
      saveMembershipPort.update(membership);
      return membership;
   }
}
