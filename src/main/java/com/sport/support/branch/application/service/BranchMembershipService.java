package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.ReleaseQuotaUC;
import com.sport.support.branch.application.port.out.UpdateQuotaPort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class BranchMembershipService implements DecreaseQuotaUC, ReleaseQuotaUC {

   private final UpdateQuotaPort updateQuotaPort;

   @Override
   public void decreaseQuota(BranchMembershipCommand command) {
      updateQuotaPort.updateQuota(command.getBranchId(), -1);
   }

   @Override
   public void releaseQuota(BranchMembershipCommand command) {
      updateQuotaPort.updateQuota(command.getBranchId(), +1);
   }
}
