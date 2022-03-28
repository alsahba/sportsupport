package com.sport.support.plan.adapter.in.web;

import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.application.port.in.command.DeletePlanCommand;
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
import java.security.Principal;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

   private final AddPlanUC addPlanUC;
   private final DeletePlanUC deletePlanUC;
   private final DeletePlanExerciseUC deletePlanExerciseUC;

   @PostMapping
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity addPlan(@Valid @RequestBody AddPlanRequest request, Principal principal) {
      addPlanUC.add(new AddPlanCommand(getAuthenticatedUserId(principal), request));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Min(1) Long id,
                                    Principal principal) {
      deletePlanUC.delete(new DeletePlanCommand(getAuthenticatedUserId(principal), id));
      return ResponseEntity.ok().build();
   }

   @DeleteMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity deletePlan(@Valid @PathVariable @Min(1) Long id,
                                    @Valid @RequestBody DeletePlanExerciseRequest request,
                                    Principal principal) {
      var command = new DeletePlanExerciseCommand(getAuthenticatedUserId(principal), id, request);
      deletePlanExerciseUC.deleteExercise(command);
      return ResponseEntity.ok().build();
   }

   private Long getAuthenticatedUserId(Principal principal) {
      return Long.valueOf(principal.getName());
   }
}