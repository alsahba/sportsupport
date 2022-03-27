package com.sport.support.exercise.application.service;

import com.sport.support.exercise.adapter.out.persistence.Exercise;
import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.application.port.in.usecase.AddExerciseUC;
import com.sport.support.exercise.application.port.in.usecase.RemoveExerciseUC;
import com.sport.support.exercise.application.port.out.RemoveExercisePort;
import com.sport.support.exercise.application.port.out.SaveExercisePort;
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
   public void create(AddExerciseCommand command) {
      saveExercisePort.save(new Exercise(command));
   }

   @Override
   @PreAuthorize("hasAuthority('EXERCISE_WRITE')")
   public void remove(Long id) {
      removeExercisePort.remove(id);
   }

}
