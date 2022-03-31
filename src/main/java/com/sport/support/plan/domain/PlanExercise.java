package com.sport.support.plan.domain;

import com.sport.support.plan.application.port.in.command.DailyPlanExerciseCommand;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PlanExercise {

   private Long id;
   private Long planId;
   private Long exerciseId;
   private int sets;
   private boolean completed;

   public PlanExercise(Long planId, DailyPlanExerciseCommand command) {
      this.planId = planId;
      this.exerciseId = command.getExerciseId();
      this.sets = command.getSets();
      this.completed = false;
   }
}
