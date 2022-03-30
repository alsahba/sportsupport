package com.sport.support.exercise.application.port.out;

import com.sport.support.exercise.domain.Exercise;

public interface SaveExercisePort {
   Exercise save(Exercise exercise);
}
