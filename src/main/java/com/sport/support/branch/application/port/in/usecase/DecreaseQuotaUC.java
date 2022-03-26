package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;

public interface DecreaseQuotaUC {
   void decreaseQuota(BranchMembershipCommand command);
}
