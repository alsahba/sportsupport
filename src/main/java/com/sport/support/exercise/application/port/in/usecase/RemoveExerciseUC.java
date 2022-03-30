package com.sport.support.exercise.application.port.in.usecase;

import com.sport.support.exercise.domain.Exercise;

public interface RemoveExerciseUC {
   Exercise remove(Long id);
}
