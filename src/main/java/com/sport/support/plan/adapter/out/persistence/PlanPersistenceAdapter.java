package com.sport.support.plan.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.adapter.out.persistence.entity.PlanExercise;
import com.sport.support.plan.adapter.out.persistence.repository.PlanExerciseRepository;
import com.sport.support.plan.adapter.out.persistence.repository.PlanRepository;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import com.sport.support.plan.domain.PlanErrorMessages;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDate;
import java.util.Optional;
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
   public Optional<Plan> loadByUserIdAndDate(Long userId, LocalDate date) {
      return planRepository.findByUserIdAndAndDate(userId, date);
   }

   @Override
   public Optional<Plan> loadByIdAndUserId(Long id, Long userId) {
      return planRepository.findByIdAndUserId(id, userId);
   }

   @Override
   public void save(Set<Plan> plans) {
      planRepository.saveAll(plans);
      planExerciseRepository.saveAll(plans.stream().flatMap(plan -> plan.getPlanExercises().stream()).collect(Collectors.toSet()));
   }

   @Override
   public void savePlanExercises(Set<PlanExercise> exercises) {
      planExerciseRepository.saveAll(exercises);
   }

   @Override
   public void remove(Long id) {
      planRepository.deleteById(id);
   }

   @Override
   public void removeExercise(Set<PlanExercise> planExercises) {
      planExerciseRepository.deleteAll(planExercises);
   }
}
