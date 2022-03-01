package com.sport.support.membership.repository;

import com.sport.support.membership.entity.MembershipHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MembershipHistoryRepository extends JpaRepository<MembershipHistory, Long> {
}
