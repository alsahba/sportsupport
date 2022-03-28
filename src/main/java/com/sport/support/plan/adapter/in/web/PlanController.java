package com.sport.support.plan.adapter.in.web;

import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.application.port.in.command.DeletePlanExerciseCommand;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

   private final AddPlanUC addPlanUC;
   private final DeletePlanUC deletePlanUC;
   private final DeletePlanExerciseUC deletePlanExerciseUC;

   // TODO: 28.03.2022 all of these endpoints must be controlled only by member's trainer
   // TODO: 28.03.2022 while addition, if plan's date exist, add only exercises to it

   @PostMapping
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity addPlan(@Valid @RequestBody AddPlanRequest request) {
      addPlanUC.add(new AddPlanCommand(request));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Min(1) Long id) {
      deletePlanUC.delete(id);
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Min(1) Long id,
                                    @Valid @RequestBody DeletePlanExerciseRequest request) {
      deletePlanExerciseUC.deleteExercise(new DeletePlanExerciseCommand(id, request));
      return ResponseEntity.ok().build();
   }

}