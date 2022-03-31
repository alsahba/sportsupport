package com.sport.support.plan.application.port.out;

import com.sport.support.plan.domain.Plan;

import java.time.LocalDate;

public interface LoadPlanPort {

   Plan load(Long id);

   Plan loadByUserIdAndDate(Long userId, LocalDate date);

   Plan loadByIdAndUserId(Long id, Long userId);

   boolean existsByUserIdAndDate(Long userId, LocalDate date);

}
