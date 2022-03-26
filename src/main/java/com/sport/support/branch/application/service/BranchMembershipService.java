package com.sport.support.branch.application.service;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.ReleaseQuotaUC;
import com.sport.support.branch.application.port.out.LoadBranchPort;
import com.sport.support.branch.application.port.out.UpdateQuotaPort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
@Transactional
public class BranchMembershipService implements DecreaseQuotaUC, ReleaseQuotaUC {

   private final LoadBranchPort loadBranchPort;
   private final UpdateQuotaPort updateQuotaPort;

   @Override
   public void decreaseQuota(BranchMembershipCommand command) {
      Branch branch = loadBranchPort.loadById(command.getBranchId());
      updateQuotaPort.updateQuota(branch, -1);
   }

   @Override
   public void releaseQuota(BranchMembershipCommand command) {
      Branch branch = loadBranchPort.loadById(command.getBranchId());
      updateQuotaPort.updateQuota(branch, +1);
   }
}
