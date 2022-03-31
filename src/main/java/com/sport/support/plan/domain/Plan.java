package com.sport.support.plan.domain;

import com.sport.support.plan.application.port.in.command.DailyPlanCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Plan {

   private Long id;
   private Long userId;
   private Long trainerId;
   private LocalDate date;
   private Set<PlanExercise> planExercises;

   public Plan(Long userId, Long trainerId, DailyPlanCommand command) {
      this.userId = userId;
      this.trainerId = trainerId;
      this.date = command.getDate();
      this.planExercises = command.getExercises().stream()
          .map(p -> new PlanExercise(id, p))
          .collect(Collectors.toSet());
   }
}
