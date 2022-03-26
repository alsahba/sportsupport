package com.sport.support.membership.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipHistory;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipHistoryRepository;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipRepository;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.MembershipErrorMessages;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, LoadMembershipPort {

   private final MembershipRepository membershipRepository;
   private final MembershipHistoryRepository membershipHistoryRepository;

   @Override
   public void save(Membership membership) {
      membershipRepository.save(membership);
      membershipHistoryRepository.save(new MembershipHistory(membership));
   }

   @Override
   public Membership loadByUserId(Long userId) {
      return membershipRepository.findByUserId(userId)
          .orElseThrow(() -> new EntityNotFoundException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_NOT_FOUND));
   }
}
