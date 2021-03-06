package com.sport.support.plan.application.service;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.employee.domain.enumeration.EmployeeErrorMessages;
import com.sport.support.membership.application.port.in.command.FindMembershipQuery;
import com.sport.support.membership.application.port.in.usecase.DoesMembershipExistUC;
import com.sport.support.plan.application.port.in.command.*;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.CompletePlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import com.sport.support.plan.domain.Plan;
import com.sport.support.plan.domain.PlanExercise;
import com.sport.support.plan.domain.enumeration.PlanErrorMessages;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class PlanService implements AddPlanUC, DeletePlanUC, DeletePlanExerciseUC, CompletePlanUC {

   private final LoadUserUC loadUserUC;
   private final DoesMembershipExistUC doesMembershipExistUC;
   private final LoadPlanPort loadPlanPort;
   private final SavePlanPort savePlanPort;
   private final RemovePlanPort removePlanPort;
   private final RemovePlanExercisePort removePlanExercisePort;

   @Override
   public List<Plan> add(AddPlanCommand addPlanCommand) {
      var user = loadUserUC.loadByUsername(addPlanCommand.getUsername());
      checkTrainerAuthorization(user.getIdVO(), addPlanCommand.getTrainerId());

      List<Plan> plans = addPlanCommand.getDayPlans().stream().map(dailyPlan -> {
         checkPlanDate(user.getId(), dailyPlan.getDate());
         return new Plan(user.getId(), addPlanCommand.getTrainerId(), dailyPlan);
      }).toList();
      return savePlanPort.save(plans);
   }

   // TODO: 30.03.2022 delete multiple plans uc
   // TODO: 30.03.2022 revert completion uc

   @Override
   public void delete(DeletePlanCommand command) {
      var plan = loadPlanPort.load(command.getPlanId());
      checkTrainerAuthorization(plan.getUserId(), command.getTrainerId());
      removePlanPort.remove(command.getPlanId());
   }

   @Override
   public void deleteExercise(DeletePlanExerciseCommand command) {
      var plan = loadPlanPort.load(command.getPlanId());
      checkTrainerAuthorization(plan.getUserId(), command.getTrainerId());

      Set<PlanExercise> planExercises = plan.getPlanExercises().stream()
          .filter(planExercise -> command.getPlanExerciseIds().contains(planExercise.getId()))
          .collect(Collectors.toSet());
      removePlanExercisePort.removeExercise(planExercises);
   }

   @Override
   public void completeExercise(CompletePlanExerciseCommand command) {
      var plan = loadPlanPort.loadByIdAndUserId(command.getPlanId(), command.getUserId());
      var planExerciseIds = plan.getPlanExercises().stream()
          .map(PlanExercise::getId)
          .filter(id -> command.getPlanExerciseIds().contains(id)).collect(Collectors.toSet());
      savePlanPort.updatePlanExercises(planExerciseIds, true);
   }

   @Override
   public void complete(CompletePlanCommand command) {
      var plan = loadPlanPort.loadByIdAndUserId(command.getPlanId(), command.getUserId());
      var planExerciseIds = plan.getPlanExercises().stream()
          .map(PlanExercise::getId).collect(Collectors.toSet());
      savePlanPort.updatePlanExercises(planExerciseIds, true);
   }

   private void checkTrainerAuthorization(UserId userId, Long trainerId) {
      if (!doesMembershipExistUC.doesExistByUserAndTrainer(new FindMembershipQuery(userId.getId(), trainerId))) {
         throw new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_TRAINER_IS_UNAUTHORIZED);
      }
   }

   private void checkPlanDate(Long userId, LocalDate date) {
      if (loadPlanPort.existsByUserIdAndDate(userId, date)) {
         throw new BusinessRuleException(PlanErrorMessages.ERROR_PLAN_DATE_IS_NOT_VALID);
      }
   }
}