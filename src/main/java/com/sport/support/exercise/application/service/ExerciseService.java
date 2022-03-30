package com.sport.support.exercise.application.service;

import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.application.port.in.usecase.AddExerciseUC;
import com.sport.support.exercise.application.port.in.usecase.RemoveExerciseUC;
import com.sport.support.exercise.application.port.out.RemoveExercisePort;
import com.sport.support.exercise.application.port.out.SaveExercisePort;
import com.sport.support.exercise.domain.Exercise;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ExerciseService implements AddExerciseUC, RemoveExerciseUC {

   private final SaveExercisePort saveExercisePort;
   private final RemoveExercisePort removeExercisePort;

   @Override
   @PreAuthorize("hasAuthority('EXERCISE_WRITE')")
   public Exercise create(AddExerciseCommand command) {
      return saveExercisePort.save(new Exercise(command));
   }

   @Override
   @PreAuthorize("hasAuthority('EXERCISE_WRITE')")
   public Exercise remove(Long id) {
      return removeExercisePort.remove(id);
   }
}
