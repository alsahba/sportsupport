package com.sport.support.plan.adapter.in.web;

import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import com.sport.support.plan.adapter.in.web.payload.CompletePlanExerciseRequest;
import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import com.sport.support.plan.application.port.in.command.*;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.CompletePlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

   private final AddPlanUC addPlanUC;
   private final DeletePlanUC deletePlanUC;
   private final DeletePlanExerciseUC deletePlanExerciseUC;
   private final CompletePlanUC completePlanUC;

   @PostMapping
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity addPlan(@Valid @RequestBody AddPlanRequest request, Principal principal) {
      addPlanUC.add(new AddPlanCommand(getAuthenticatedUserId(principal), request));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Positive Long id,
                                    Principal principal) {
      deletePlanUC.delete(new DeletePlanCommand(getAuthenticatedUserId(principal), id));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Positive Long id,
                                    @Valid @RequestBody DeletePlanExerciseRequest request,
                                    Principal principal) {
      var command = new DeletePlanExerciseCommand(getAuthenticatedUserId(principal), id, request);
      deletePlanExerciseUC.deleteExercise(command);
      return ResponseEntity.ok().build();
   }

   @PostMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('MEMBERSHIP_WRITE')")
   public ResponseEntity completeExercises(@Valid @PathVariable @Positive Long id,
                                           @Valid @RequestBody CompletePlanExerciseRequest request,
                                           Principal principal) {
      var command = new CompletePlanExerciseCommand(id, getAuthenticatedUserId(principal), request);
      completePlanUC.completeExercise(command);
      return ResponseEntity.ok().build();
   }

   @PostMapping("/{id}")
   @PreAuthorize("hasAuthority('MEMBERSHIP_WRITE')")
   public ResponseEntity complete(@Valid @PathVariable @Positive Long id,
                                           Principal principal) {
      var command = new CompletePlanCommand(id, getAuthenticatedUserId(principal));
      completePlanUC.complete(command);
      return ResponseEntity.ok().build();
   }

   private Long getAuthenticatedUserId(Principal principal) {
      return Long.valueOf(principal.getName());
   }
}