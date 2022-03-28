package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import lombok.Getter;

import java.util.Set;

@Getter
public class DeletePlanExerciseCommand {

   private final Long trainerId;
   private final Long planId;
   private final Set<Long> planExerciseIds;

   public DeletePlanExerciseCommand(Long trainerId, Long planId, DeletePlanExerciseRequest request) {
      this.trainerId = trainerId;
      this.planId = planId;
      this.planExerciseIds = request.getPlanExerciseIds();
   }
}
