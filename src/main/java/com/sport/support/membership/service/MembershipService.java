package com.sport.support.membership.service;

import com.sport.support.appuser.adapter.out.entity.AppUser;
import com.sport.support.appuser.application.service.AppUserDetailsManager;
import com.sport.support.branch.service.BranchService;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.entity.Membership;
import com.sport.support.membership.entity.MembershipHistory;
import com.sport.support.membership.entity.enumeration.Status;
import com.sport.support.membership.messages.MembershipErrorMessages;
import com.sport.support.membership.repository.MembershipHistoryRepository;
import com.sport.support.membership.repository.MembershipRepository;
import com.sport.support.wallet.application.port.in.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.WithdrawMoneyUC;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipService {

   private final MembershipRepository membershipRepository;
   private final AppUserDetailsManager appUserDetailsManager;
   private final WithdrawMoneyUC withdrawMoneyUC;
   private final MembershipHistoryRepository membershipHistoryRepository;
   private final BranchService branchService;

   @Transactional
   public void add(Membership membership) {
      checkUserIsAlreadyMember(membership.getUser().getId());
      branchService.checkout(membership);

      AppUser user = appUserDetailsManager.findById(membership.getUser().getId());
      membership.setUser(user);

      Money cost = membership.getBranch().getCost(membership.getType(), membership.getDuration());
      withdrawMoneyUC.withdraw(new WithdrawMoneyCommand(user.getId(), cost));
      membershipRepository.save(membership);
      membershipHistoryRepository.save(new MembershipHistory(membership));
   }

   public void cancel(Long userId) {
      Membership membership = membershipRepository.findByUserId(userId)
          .orElseThrow(() -> new EntityNotFoundException("User not found"));
      membership.setStatus(Status.CANCELLED);
      branchService.releaseQuota(membership.getBranch());
      membershipRepository.save(membership);
   }

   private void checkUserIsAlreadyMember(Long userId) {
      if (membershipRepository.existsByUserId(userId))
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER);
   }
}
