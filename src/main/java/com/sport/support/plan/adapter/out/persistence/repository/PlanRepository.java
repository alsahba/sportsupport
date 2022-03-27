package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PlanRepository extends JpaRepository<Plan, Long> {
}
