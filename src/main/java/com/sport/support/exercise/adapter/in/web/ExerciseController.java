package com.sport.support.exercise.adapter.in.web;

import com.sport.support.exercise.adapter.in.web.payload.AddExerciseRequest;
import com.sport.support.exercise.adapter.in.web.payload.ExerciseResponse;
import com.sport.support.exercise.application.port.in.command.AddExerciseCommand;
import com.sport.support.exercise.application.port.in.usecase.AddExerciseUC;
import com.sport.support.exercise.application.port.in.usecase.RemoveExerciseUC;
import com.sport.support.infrastructure.abstractions.adapters.web.AbstractRestController;
import com.sport.support.infrastructure.common.web.Response;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/exercises")
@RequiredArgsConstructor
public class ExerciseController extends AbstractRestController {

   private final AddExerciseUC addExerciseUC;
   private final RemoveExerciseUC removeExerciseUC;

   @PostMapping
   @ResponseStatus(value = HttpStatus.CREATED)
   public Response<ExerciseResponse> create(@Valid @RequestBody AddExerciseRequest request) {
      var exercise = addExerciseUC.create(new AddExerciseCommand(request));
      return respond(ExerciseResponse.success(exercise));
   }

   @DeleteMapping("/{id}")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<ExerciseResponse> remove(@PathVariable("id") Long id) {
      var exercise = removeExerciseUC.remove(id);
      return respond(ExerciseResponse.success(exercise));
   }
}
