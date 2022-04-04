package com.sport.support.plan.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.plan.application.port.in.command.DailyPlanCommand;
import com.sport.support.plan.domain.vo.PlanId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@SuperBuilder
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
}
