package com.sport.support.plan.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.plan.application.port.in.command.DailyPlanCommand;
import com.sport.support.plan.domain.vo.PlanId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
public class Plan extends AbstractDomainObject<PlanId> {

   private UserId userId;
   private UserId trainerId;
   private LocalDate date;
   private Set<PlanExercise> planExercises;

   public Plan(Long userId, Long trainerId, DailyPlanCommand command) {
      this.userId = new UserId(userId);
      this.trainerId = new UserId(trainerId);
      this.date = command.getDate();
      this.planExercises = command.getExercises().stream()
          .map(p -> new PlanExercise(getIdVO(), p))
          .collect(Collectors.toSet());
   }

   @Builder
   public Plan(PlanId idVO, UserId userId, UserId trainerId, LocalDate date, Set<PlanExercise> planExercises) {
      super(idVO);
      this.userId = userId;
      this.trainerId = trainerId;
      this.date = date;
      this.planExercises = planExercises;
   }
}
