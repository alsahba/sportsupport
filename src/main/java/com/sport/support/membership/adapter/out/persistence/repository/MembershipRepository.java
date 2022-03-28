package com.sport.support.membership.adapter.out.persistence.repository;

import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

   Optional<Membership> findByUserId(Long userId);

   boolean existsByUserIdAndTrainerId(Long userId, Long trainerId);

}
