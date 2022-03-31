package com.sport.support.branch.adapter.out.persistence.repository;

import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<BranchEntity, Long> {
}
