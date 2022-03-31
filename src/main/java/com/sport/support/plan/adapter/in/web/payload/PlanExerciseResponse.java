package com.sport.support.plan.adapter.in.web.payload;

import com.sport.support.plan.domain.PlanExercise;
import lombok.Getter;

@Getter
public class PlanExerciseResponse {

   private final Long id;
   private final Long exerciseId;
   private final int sets;
   private final boolean completed;

   public PlanExerciseResponse(PlanExercise planExercise) {
      this.id = planExercise.getId();
      this.exerciseId = planExercise.getExerciseId();
      this.sets = planExercise.getSets();
      this.completed = planExercise.isCompleted();
   }
}
