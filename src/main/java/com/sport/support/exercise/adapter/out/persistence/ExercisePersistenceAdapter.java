package com.sport.support.exercise.adapter.out.persistence;

import com.sport.support.exercise.application.port.out.RemoveExercisePort;
import com.sport.support.exercise.application.port.out.SaveExercisePort;
import com.sport.support.exercise.domain.Exercise;
import com.sport.support.exercise.domain.ExerciseErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class ExercisePersistenceAdapter implements SaveExercisePort, RemoveExercisePort {

   private final ExerciseRepository exerciseRepository;

   @Override
   public Exercise save(Exercise exercise) {
      var exerciseEntity = new ExerciseEntity(exercise);
      return exerciseRepository.save(exerciseEntity).toDomain();
   }

   @Override
   public Exercise remove(Long id) {
      var entity = exerciseRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(ExerciseErrorMessages.ERROR_EXERCISE_IS_NOT_FOUND));
      exerciseRepository.delete(entity);
      return entity.toDomain();
   }
}
