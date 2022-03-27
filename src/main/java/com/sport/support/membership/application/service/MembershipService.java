package com.sport.support.membership.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.application.port.in.usecase.ReleaseQuotaUC;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.in.usecase.CancelMembershipUC;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.MembershipErrorMessages;
import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class MembershipService implements AddMembershipUC, CancelMembershipUC {

   // TODO: 27.03.2022 trainer assign to member uc

   private final WithdrawMoneyUC withdrawMoneyUC;
   private final DecreaseQuotaUC decreaseQuotaUC;
   private final ReleaseQuotaUC releaseQuotaUC;
   private final LoadUserUC loadUserUC;
   private final FindBranchUC findBranchUC;
   private final SaveMembershipPort saveMembershipPort;
   private final LoadMembershipPort loadMembershipPort;

   @Override
   public void add(AddMembershipCommand command) {
      Membership membership = new Membership(command);
      checkUserIsAlreadyMember(membership.getUser().getId());

      Branch branch = findBranchUC.findById(new FindBranchQuery(membership.getBranch().getId()));
      membership.setBranch(branch);

      decreaseQuotaUC.decreaseQuota(new BranchMembershipCommand(branch));

      // TODO: 27.03.2022 upgrade user permissions to member
      AppUser user = loadUserUC.loadById(membership.getUser().getId());
      membership.setUser(user);

      Money cost = branch.getCost(membership.getType(), membership.getDuration());
      withdrawMoneyUC.withdraw(new WithdrawMoneyCommand(user.getId(), cost));
      saveMembershipPort.save(membership);
   }

   @Override
   public void cancel(Long userId) {
      Membership membership = loadMembershipPort.loadByUserId(userId)
          .orElseThrow(() -> new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_NOT_FOUND));
      membership.cancel();
      releaseQuotaUC.releaseQuota(new BranchMembershipCommand(membership.getBranch()));
      saveMembershipPort.save(membership);
   }

   private void checkUserIsAlreadyMember(Long userId) {
      if (loadMembershipPort.loadByUserId(userId).isPresent())
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER);
   }
}
