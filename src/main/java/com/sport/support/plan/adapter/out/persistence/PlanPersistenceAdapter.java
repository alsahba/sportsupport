package com.sport.support.plan.adapter.out.persistence;

import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.plan.adapter.out.persistence.entity.PlanEntity;
import com.sport.support.plan.adapter.out.persistence.repository.PlanExerciseRepository;
import com.sport.support.plan.adapter.out.persistence.repository.PlanRepository;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import com.sport.support.plan.domain.Plan;
import com.sport.support.plan.domain.PlanErrorMessages;
import com.sport.support.plan.domain.PlanExercise;
import lombok.RequiredArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@PersistenceAdapter
@RequiredArgsConstructor
public class PlanPersistenceAdapter implements LoadPlanPort, SavePlanPort, RemovePlanPort, RemovePlanExercisePort {

   private final PlanRepository planRepository;
   private final PlanExerciseRepository planExerciseRepository;

   @Override
   public Plan load(Long id) {
      var entity = planRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(PlanErrorMessages.ERROR_PLAN_IS_NOT_FOUND));
      return entity.toDomain();
   }

   @Override
   public Plan loadByUserIdAndDate(Long userId, LocalDate date) {
      return planRepository.findByUserIdAndAndDate(userId, date).map(PlanEntity::toDomain).orElse(null);
   }

   @Override
   public Plan loadByIdAndUserId(Long id, Long userId) {
      return planRepository.findByIdAndUserId(id, userId).map(PlanEntity::toDomain)
          .orElseThrow(() -> new BusinessRuleException(PlanErrorMessages.ERROR_PLAN_IS_NOT_FOUND));
   }

   @Override
   public List<Plan> save(List<Plan> plans) {
      var planEntities = plans.stream().map(PlanEntity::new).collect(Collectors.toSet());
      var savedPlanEntities = planRepository.saveAll(planEntities);
      planExerciseRepository.saveAll(planEntities.stream()
          .flatMap(plan -> plan.getPlanExerciseEntities().stream()).collect(Collectors.toSet()));

      return savedPlanEntities.stream().map(PlanEntity::toDomain).collect(Collectors.toList());
   }

   @Override
   public void updatePlanExercises(Set<Long> ids, boolean completed) {
      var planExcerciseEntities = planExerciseRepository.findByIdIn(ids);
      planExcerciseEntities.forEach(planExerciseEntity -> planExerciseEntity.setCompleted(completed));
      planExerciseRepository.saveAll(planExcerciseEntities);
   }

   @Override
   public void remove(Long id) {
      planRepository.deleteById(id);
   }

   @Override
   public void removeExercise(Set<PlanExercise> planExercises) {
      planExerciseRepository.deleteByIdIn(planExercises.stream().map(PlanExercise::getId).collect(Collectors.toSet()));
   }

   @Override
   public boolean existsByUserIdAndDate(Long userId, LocalDate date) {
      return planRepository.existsByUserIdAndDate(userId, date);
   }
}
