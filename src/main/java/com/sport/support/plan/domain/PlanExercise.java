package com.sport.support.plan.domain;

import com.sport.support.exercise.domain.vo.ExerciseId;
import com.sport.support.plan.application.port.in.command.DailyPlanExerciseCommand;
import com.sport.support.plan.domain.vo.PlanExerciseId;
import com.sport.support.plan.domain.vo.PlanId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PlanExercise extends AbstractDomainObject<PlanExerciseId> {

   private PlanId planId;
   private ExerciseId exerciseId;
   private int sets;
   private boolean completed;

   public PlanExercise(PlanId planId, DailyPlanExerciseCommand command) {
      this.planId = planId;
      this.exerciseId = new ExerciseId(command.getExerciseId());
      this.sets = command.getSets();
      this.completed = false;
   }

   @Builder
   public PlanExercise(PlanExerciseId idVO, PlanId planId, ExerciseId exerciseId, int sets, boolean completed) {
      super(idVO);
      this.planId = planId;
      this.exerciseId = exerciseId;
      this.sets = sets;
      this.completed = completed;
   }
}
