package com.sport.support.plan.application.service;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.membership.application.port.in.command.FindMembershipQuery;
import com.sport.support.membership.application.port.in.usecase.DoesMembershipExistUC;
import com.sport.support.membership.domain.MembershipErrorMessages;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.adapter.out.persistence.entity.PlanExercise;
import com.sport.support.plan.application.port.in.command.*;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.CompletePlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import com.sport.support.plan.application.port.out.LoadPlanPort;
import com.sport.support.plan.application.port.out.RemovePlanExercisePort;
import com.sport.support.plan.application.port.out.RemovePlanPort;
import com.sport.support.plan.application.port.out.SavePlanPort;
import com.sport.support.plan.domain.PlanErrorMessages;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.function.Consumer;
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
   public void add(AddPlanCommand addPlanCommand) {
      var user = loadUserUC.loadByUsername(addPlanCommand.getUsername());
      isTrainerAuthorized(user.getId(), addPlanCommand.getTrainerId());

      Set<Plan> plans = new HashSet<>();
      addPlanCommand.getDayPlans().forEach(dayPlan -> {
             Optional<Plan> plan = loadPlanPort.loadByUserIdAndDate(user.getId(), dayPlan.getDate());
             plan.ifPresentOrElse(addNewExercises(dayPlan), () -> plans.add(new Plan(dayPlan, user)));
          }
      );

      savePlanPort.save(plans);
   }

   @Override
   public void delete(DeletePlanCommand command) {
      var plan = loadPlanPort.load(command.getPlanId());
      isTrainerAuthorized(command.getTrainerId(), plan.getUser().getId());
      removePlanPort.remove(command.getPlanId());
   }

   @Override
   public void deleteExercise(DeletePlanExerciseCommand command) {
      var plan = loadPlanPort.load(command.getPlanId());
      isTrainerAuthorized(plan.getUser().getId(), command.getTrainerId());

      Set<PlanExercise> planExercises = plan.getPlanExercises().stream()
          .filter(planExercise -> command.getPlanExerciseIds().contains(planExercise.getId()))
          .collect(Collectors.toSet());
      removePlanExercisePort.removeExercise(planExercises);
   }

   @Override
   public void completeExercise(CompletePlanExerciseCommand command) {
      var optionalPlan = loadPlanPort.loadByIdAndUserId(command.getPlanId(), command.getUserId());

      optionalPlan.ifPresentOrElse(plan -> {
         plan.getPlanExercises().stream()
             .filter(planExercise -> command.getPlanExerciseIds().contains(planExercise.getId()))
             .forEach(planExercise -> planExercise.setCompleted(true));
         savePlanPort.savePlanExercises(plan.getPlanExercises());
      }, () -> {
         throw new BusinessRuleException(PlanErrorMessages.ERROR_PLAN_IS_NOT_FOUND);
      });
   }

   @Override
   public void complete(CompletePlanCommand command) {
      var optionalPlan = loadPlanPort.loadByIdAndUserId(command.getPlanId(), command.getUserId());
      optionalPlan.ifPresentOrElse(plan -> {
         plan.getPlanExercises().forEach(planExercise -> planExercise.setCompleted(true));
         savePlanPort.savePlanExercises(plan.getPlanExercises());
      }, () -> {
         throw new BusinessRuleException(PlanErrorMessages.ERROR_PLAN_IS_NOT_FOUND);
      });
   }

   private void isTrainerAuthorized(Long userId, Long trainerId) {
      if (!doesMembershipExistUC.doesExistByUserAndTrainer(new FindMembershipQuery(userId, trainerId))) {
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_TRAINER_IS_UNAUTHORIZED);
      }
   }

   private Consumer<Plan> addNewExercises(DailyPlanCommand dayPlan) {
      return plan -> {
         plan.getPlanExercises().addAll(dayPlan.getExercises().stream()
             .map(planExercise -> new PlanExercise(planExercise, plan)).collect(Collectors.toSet()));
         savePlanPort.savePlanExercises(plan.getPlanExercises());
      };
   }
}