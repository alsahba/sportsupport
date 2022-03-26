package com.sport.support.branch.adapter.out.persistence.repository;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BranchRepository extends JpaRepository<Branch, Long> {
}
