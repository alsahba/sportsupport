package com.sport.support.plan.application.service;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.application.port.in.command.DeletePlanExerciseCommand;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService implements AddPlanUC, DeletePlanUC, DeletePlanExerciseUC {

   private final LoadUserUC loadUserUC;
   private final LoadPlanPort loadPlanPort;
   private final SavePlanPort savePlanPort;
   private final RemovePlanPort removePlanPort;
   private final RemovePlanExercisePort removePlanExercisePort;

   @Override
   public void add(AddPlanCommand addPlanCommand) {
      var user = loadUserUC.loadByUsername(addPlanCommand.getUsername());
      var plans = addPlanCommand.getDayPlans().stream()
          .map(dayPlan -> new Plan(dayPlan, user)).collect(Collectors.toSet());
      savePlanPort.save(plans);
   }

   @Override
   public void delete(Long id) {
      removePlanPort.remove(id);
   }

   @Override
   public void deleteExercise(DeletePlanExerciseCommand command) {
      Plan plan = loadPlanPort.load(command.getId());
      Set<Long> filteredIdList = plan.getPlanExercises().stream()
          .map(AbstractEntity::getId)
          .filter(id -> command.getPlanExerciseIds().contains(id))
          .collect(Collectors.toSet());
      removePlanExercisePort.removeExercise(filteredIdList);
   }
}