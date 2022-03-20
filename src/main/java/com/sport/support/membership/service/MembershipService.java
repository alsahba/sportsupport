package com.sport.support.membership.service;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.appuser.service.AppUserDetailsManager;
import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.service.BranchService;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import com.sport.support.membership.entity.Membership;
import com.sport.support.membership.entity.MembershipHistory;
import com.sport.support.membership.entity.enumeration.Status;
import com.sport.support.membership.repository.MembershipHistoryRepository;
import com.sport.support.membership.repository.MembershipRepository;
import com.sport.support.wallet.service.WalletService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class MembershipService {

   private final MembershipRepository membershipRepository;
   private final AppUserDetailsManager appUserDetailsManager;
   private final WalletService walletService;
   private final MembershipHistoryRepository membershipHistoryRepository;
   private final BranchService branchService;

   @Transactional
   public void add(Membership membership) {
      Branch branch = branchService.retrieveById(membership.getBranch().getId());
      membership.setBranch(branch);

      AppUser user = appUserDetailsManager.retrieveById(membership.getUser().getId());

      membership.setUser(user);
      Money cost = branch.getCost(membership.getType(), membership.getDuration());

      walletService.withdraw(user, cost);
      membershipRepository.save(membership);
      membershipHistoryRepository.save(new MembershipHistory(membership));
   }

   public void cancel(Long userId) {
      Membership membership = membershipRepository.findByUserId(userId)
          .orElseThrow(() -> new RecordIsNotFoundException("User not found"));
      membership.setStatus(Status.CANCELLED);
      membershipRepository.save(membership);
   }
}
