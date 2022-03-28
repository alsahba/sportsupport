package com.sport.support.plan.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.adapter.out.persistence.repository.PlanExerciseRepository;
import com.sport.support.plan.adapter.out.persistence.repository.PlanRepository;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import com.sport.support.plan.domain.PlanErrorMessages;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class PlanPersistenceAdapter implements LoadPlanPort, SavePlanPort, RemovePlanPort, RemovePlanExercisePort {

   private final PlanRepository planRepository;
   private final PlanExerciseRepository planExerciseRepository;

   @Override
   public Plan load(Long id) {
      return planRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(PlanErrorMessages.ERROR_PLAN_IS_NOT_FOUND));
   }

   @Override
   public void save(Set<Plan> plans) {
      planRepository.saveAll(plans);
      planExerciseRepository.saveAll(plans.stream().flatMap(plan -> plan.getPlanExercises().stream()).collect(Collectors.toSet()));
   }

   @Override
   public void remove(Long id) {
      planRepository.deleteById(id);
   }

   @Override
   public void removeExercise(Set<Long> ids) {
      planExerciseRepository.deleteByIdIn(ids);
   }
}
