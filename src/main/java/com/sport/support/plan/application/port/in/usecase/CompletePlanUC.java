package com.sport.support.plan.application.port.in.usecase;

import com.sport.support.plan.application.port.in.command.CompletePlanCommand;
import com.sport.support.plan.application.port.in.command.CompletePlanExerciseCommand;

public interface CompletePlanUC {

   void completeExercise(CompletePlanExerciseCommand command);

   void complete(CompletePlanCommand command);

}
