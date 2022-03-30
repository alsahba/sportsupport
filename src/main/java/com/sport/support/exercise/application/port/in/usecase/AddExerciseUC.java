package com.sport.support.exercise.application.port.in.usecase;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.domain.Exercise;

public interface AddExerciseUC {
   Exercise create(AddExerciseCommand command);
}
