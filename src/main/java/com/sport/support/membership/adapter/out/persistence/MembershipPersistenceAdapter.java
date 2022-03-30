package com.sport.support.membership.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipEntity;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipHistoryEntity;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipHistoryRepository;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipRepository;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.Membership;
import com.sport.support.membership.domain.MembershipErrorMessages;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, LoadMembershipPort, DoesMembershipExistPort {

   private final MembershipRepository membershipRepository;
   private final MembershipHistoryRepository membershipHistoryRepository;

   @Override
   public Membership save(Membership membership) {
      MembershipEntity membershipEntity = membershipRepository.save(new MembershipEntity(membership));
      membershipHistoryRepository.save(new MembershipHistoryEntity(membershipEntity));
      return membershipEntity.toDomain();
   }

   @Override
   public void update(Membership membership) {
      MembershipEntity membershipEntity = findByUserId(membership.getUserId());
      membershipEntity.setStatus(membership.getStatus());
      membershipEntity.setTrainer(new AppUser(membership.getTrainerId()));
      membershipRepository.save(membershipEntity);
   }

   @Override
   public Membership loadByUserId(Long userId) {
      return findByUserId(userId).toDomain();
   }

   @Override
   public boolean doesExistByUserAndTrainer(Long userId, Long trainerId) {
      return membershipRepository.existsByUserIdAndTrainerId(userId, trainerId);
   }

   @Override
   public boolean doesExistByUser(Long userId) {
      return membershipRepository.existsByUserId(userId);
   }

   private MembershipEntity findByUserId(Long userId) {
      return membershipRepository.findByUserId(userId)
          .orElseThrow(() -> new EntityNotFoundException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_NOT_FOUND));
   }
}
