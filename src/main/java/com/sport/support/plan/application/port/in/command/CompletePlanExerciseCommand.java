package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.CompletePlanExerciseRequest;
import lombok.Getter;

import java.util.Set;

@Getter
public class CompletePlanExerciseCommand {

   private final Long planId;
   private final Set<Long> planExerciseIds;
   private final Long userId;

   public CompletePlanExerciseCommand(Long planId, Long userId, CompletePlanExerciseRequest request) {
      this.planId = planId;
      this.userId = userId;
      this.planExerciseIds = request.getPlanExerciseIds();
   }
}
