package com.sport.support.membership.application.service;

import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.usecase.ReleaseQuotaUC;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import com.sport.support.membership.application.port.in.usecase.CancelMembershipUC;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.MembershipErrorMessages;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class CancelMembershipService implements CancelMembershipUC {

   private final ReleaseQuotaUC releaseQuotaUC;
   private final SaveMembershipPort saveMembershipPort;
   private final LoadMembershipPort loadMembershipPort;

   @Override
   public void cancel(Long userId) {
      Membership membership = loadMembershipPort.loadByUserId(userId)
          .orElseThrow(() -> new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_NOT_FOUND));
      membership.cancel();
      releaseQuotaUC.releaseQuota(new BranchMembershipCommand(membership.getBranch()));
      saveMembershipPort.save(membership);
   }
}
