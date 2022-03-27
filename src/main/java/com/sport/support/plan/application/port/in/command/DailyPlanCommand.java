package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.AddDayPlanRequest;
import lombok.Getter;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class DailyPlanCommand {

   private final LocalDate date;
   private final Set<DailyPlanExerciseCommand> exercises;

   public DailyPlanCommand(AddDayPlanRequest request) {
      this.date = request.getDate();
      this.exercises = request.getExercises().stream().map(DailyPlanExerciseCommand::new).collect(Collectors.toSet());
   }
}
