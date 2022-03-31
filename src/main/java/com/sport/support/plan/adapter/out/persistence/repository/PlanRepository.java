package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.PlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface PlanRepository extends JpaRepository<PlanEntity, Long> {

   Optional<PlanEntity> findByUserIdAndAndDate(Long userId, LocalDate date);

   Optional<PlanEntity> findByIdAndUserId(Long id, Long userId);

   boolean existsByUserIdAndDate(Long userId, LocalDate date);

}
