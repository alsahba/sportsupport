package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import lombok.Getter;

import java.util.Set;

@Getter
public class DeletePlanExerciseCommand {

   private final Long id;
   private final Set<Long> planExerciseIds;

   public DeletePlanExerciseCommand(Long planId, DeletePlanExerciseRequest request) {
      this.id = planId;
      this.planExerciseIds = request.getPlanExerciseIds();
   }
}
