package com.sport.support.exercise.application.port.out;

import com.sport.support.exercise.adapter.out.persistence.Exercise;

public interface SaveExercisePort {
   void save(Exercise exercise);
}
