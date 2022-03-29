package com.sport.support.plan.application.port.out;

import com.sport.support.plan.adapter.out.persistence.entity.Plan;

import java.time.LocalDate;
import java.util.Optional;

public interface LoadPlanPort {

   Plan load(Long id);

   Optional<Plan> loadByUserIdAndDate(Long userId, LocalDate date);

   Optional<Plan> loadByIdAndUserId(Long id, Long userId);

}
