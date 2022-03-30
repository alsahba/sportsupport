package com.sport.support.membership.adapter.out.persistence.repository;

import com.sport.support.membership.adapter.out.persistence.entity.MembershipHistoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipHistoryRepository extends JpaRepository<MembershipHistoryEntity, Long> {
}
