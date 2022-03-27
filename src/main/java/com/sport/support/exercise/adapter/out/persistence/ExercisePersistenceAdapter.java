package com.sport.support.exercise.adapter.out.persistence;

import com.sport.support.exercise.application.port.out.RemoveExercisePort;
import com.sport.support.exercise.application.port.out.SaveExercisePort;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExercisePersistenceAdapter implements SaveExercisePort, RemoveExercisePort {

   private final ExerciseRepository exerciseRepository;

   @Override
   public void save(Exercise exercise) {
      exerciseRepository.save(exercise);
   }

   @Override
   public void remove(Long id) {
      exerciseRepository.deleteById(id);
   }
}
