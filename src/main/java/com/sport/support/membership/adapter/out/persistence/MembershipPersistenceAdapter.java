package com.sport.support.membership.adapter.out.persistence;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipEntity;
import com.sport.support.membership.adapter.out.persistence.entity.MembershipHistoryEntity;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipHistoryRepository;
import com.sport.support.membership.adapter.out.persistence.repository.MembershipRepository;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.Membership;
import com.sport.support.membership.domain.enumeration.MembershipErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class MembershipPersistenceAdapter implements SaveMembershipPort, LoadMembershipPort,
    DoesMembershipExistPort {

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
      MembershipEntity updatedEntity = new MembershipEntity(membership);
      MembershipEntity entity = findByUserId(membership.getUserId().getId());
      updatedEntity.update(entity);

      membershipRepository.save(updatedEntity);
   }

   @Override
   public Membership loadByUserId(UserId userId) {
      return findByUserId(userId.getId()).toDomain();
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
          .orElseThrow(() -> new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_IS_NOT_FOUND));
   }
}
