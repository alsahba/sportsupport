package com.sport.support.plan.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.adapter.out.persistence.repository.PlanExerciseRepository;
import com.sport.support.plan.adapter.out.persistence.repository.PlanRepository;
import com.sport.support.plan.application.port.out.SavePlanPort;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
@Transactional
public class PlanPersistenceAdapter implements SavePlanPort {

   private final PlanRepository planRepository;
   private final PlanExerciseRepository planExerciseRepository;

   @Override
   public void save(Set<Plan> plans) {
      planRepository.saveAll(plans);
      planExerciseRepository.saveAll(plans.stream().flatMap(plan -> plan.getPlanExercises().stream()).collect(Collectors.toSet()));
   }
}
