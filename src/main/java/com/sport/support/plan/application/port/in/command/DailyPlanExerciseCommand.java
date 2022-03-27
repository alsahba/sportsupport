package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.AddPlanExerciseRequest;
import lombok.Getter;

@Getter
public class DailyPlanExerciseCommand {

   private final Long id;
   private final int sets;

   public DailyPlanExerciseCommand(AddPlanExerciseRequest request) {
      this.id = request.getId();
      this.sets = request.getSets();
   }
}
