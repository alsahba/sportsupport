package com.sport.support.membership.adapter.out.persistence.repository;

import com.sport.support.membership.adapter.out.persistence.entity.MembershipEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<MembershipEntity, Long> {

   Optional<MembershipEntity> findByUserId(Long userId);

   boolean existsByUserIdAndTrainerId(Long userId, Long trainerId);

   boolean existsByUserId(Long userId);

}
