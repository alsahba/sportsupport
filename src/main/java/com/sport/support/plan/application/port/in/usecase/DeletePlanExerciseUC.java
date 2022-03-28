package com.sport.support.plan.application.port.in.usecase;

import com.sport.support.plan.application.port.in.command.DeletePlanExerciseCommand;

public interface DeletePlanExerciseUC {
   void deleteExercise(DeletePlanExerciseCommand command);
}
