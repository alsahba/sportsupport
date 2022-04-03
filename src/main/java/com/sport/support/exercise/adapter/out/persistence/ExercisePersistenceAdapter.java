package com.sport.support.exercise.adapter.out.persistence;

import com.sport.support.exercise.application.port.out.RemoveExercisePort;
import com.sport.support.exercise.application.port.out.SaveExercisePort;
import com.sport.support.exercise.domain.Exercise;
import com.sport.support.exercise.domain.enumeration.ExerciseErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

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
          .orElseThrow(() -> new BusinessRuleException(ExerciseErrorMessages.ERROR_EXERCISE_IS_NOT_FOUND));
      exerciseRepository.delete(entity);
      return entity.toDomain();
   }
}
