package com.sport.support.exercise.application.port.in.usecase;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;

public interface AddExerciseUC {
   void create(AddExerciseCommand command);
}
