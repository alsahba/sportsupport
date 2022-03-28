package com.sport.support.membership.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipHistory;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipHistoryRepository;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipRepository;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import lombok.RequiredArgsConstructor;

import java.util.Optional;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, LoadMembershipPort, DoesMembershipExistPort {

   private final MembershipRepository membershipRepository;
   private final MembershipHistoryRepository membershipHistoryRepository;

   @Override
   public void save(Membership membership) {
      membershipRepository.save(membership);
      membershipHistoryRepository.save(new MembershipHistory(membership));
   }

   @Override
   public Optional<Membership> loadByUserId(Long userId) {
      return membershipRepository.findByUserId(userId);
   }

   @Override
   public boolean doesExist(Long userId, Long trainerId) {
      return membershipRepository.existsByUserIdAndTrainerId(userId, trainerId);
   }
}
