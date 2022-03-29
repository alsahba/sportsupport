package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<Plan, Long> {

   Optional<Plan> findByUserIdAndAndDate(Long userId, LocalDate date);

   Optional<Plan> findByIdAndUserId(Long id, Long userId);
}
