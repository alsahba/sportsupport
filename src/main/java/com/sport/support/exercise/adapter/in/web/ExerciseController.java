package com.sport.support.exercise.adapter.in.web;

import com.sport.support.exercise.adapter.in.web.payload.AddExerciseRequest;
import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.application.port.in.usecase.AddExerciseUC;
import com.sport.support.exercise.application.port.in.usecase.RemoveExerciseUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController {

   private final AddExerciseUC addExerciseUC;
   private final RemoveExerciseUC removeExerciseUC;

   @PostMapping
   public ResponseEntity create(@Valid @RequestBody AddExerciseRequest request) {
      addExerciseUC.create(new AddExerciseCommand(request));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   public ResponseEntity remove(@PathVariable("id") Long id) {
      removeExerciseUC.remove(id);
      return ResponseEntity.ok().build();
   }
}
