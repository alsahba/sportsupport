package com.sport.support.membership.adapter.out.persistence;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MembershipRepository extends JpaRepository<Membership, Long> {

   Optional<Membership> findByUserId(Long userId);

   boolean existsByUserId(Long userId);

}
