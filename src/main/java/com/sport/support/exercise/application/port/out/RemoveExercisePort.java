package com.sport.support.exercise.application.port.out;

import com.sport.support.exercise.domain.Exercise;

public interface RemoveExercisePort {
   Exercise remove(Long id);
}
