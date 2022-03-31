package com.sport.support.plan.adapter.in.web;

import com.sport.support.infrastructure.abstractions.adapters.web.AbstractController;
import com.sport.support.infrastructure.common.web.DataResponse;
import com.sport.support.infrastructure.common.web.Response;
import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import com.sport.support.plan.adapter.in.web.payload.CompletePlanExerciseRequest;
import com.sport.support.plan.adapter.in.web.payload.DeletePlanExerciseRequest;
import com.sport.support.plan.adapter.in.web.payload.PlanResponse;
import com.sport.support.plan.application.port.in.command.*;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.in.usecase.CompletePlanUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanExerciseUC;
import com.sport.support.plan.application.port.in.usecase.DeletePlanUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.Positive;
import java.security.Principal;
import java.util.Collection;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController extends AbstractController {

   private final AddPlanUC addPlanUC;
   private final DeletePlanUC deletePlanUC;
   private final DeletePlanExerciseUC deletePlanExerciseUC;
   private final CompletePlanUC completePlanUC;

   // TODO: 30.03.2022 find by day/week uc

   @PostMapping
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   @ResponseStatus(value = HttpStatus.CREATED)
   public Response<DataResponse<PlanResponse>> addPlan(@Valid @RequestBody AddPlanRequest request, Principal principal) {
      var plans = addPlanUC.add(new AddPlanCommand(getAuthenticatedUserId(principal), request));
      var response = plans.stream().map(PlanResponse::new).collect(Collectors.toList());
      return respond(response);
   }

   @DeleteMapping("/{id}")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<Long> deletePlan(@Valid @PathVariable @Positive Long id, Principal principal) {
      deletePlanUC.delete(new DeletePlanCommand(getAuthenticatedUserId(principal), id));
      return respond(id);
   }

   @DeleteMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<Long> deletePlan(@Valid @PathVariable @Positive Long id,
                                    @Valid @RequestBody DeletePlanExerciseRequest request, Principal principal) {
      var command = new DeletePlanExerciseCommand(getAuthenticatedUserId(principal), id, request);
      deletePlanExerciseUC.deleteExercise(command);
      return respond(id);
   }

   @PostMapping("/{id}/exercise")
   @PreAuthorize("hasAuthority('MEMBERSHIP_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<Collection<Long>> completeExercises(@Valid @PathVariable @Positive Long id,
                                                       @Valid @RequestBody CompletePlanExerciseRequest request,
                                                       Principal principal) {
      var command = new CompletePlanExerciseCommand(id, getAuthenticatedUserId(principal), request);
      completePlanUC.completeExercise(command);
      return respond(request.getPlanExerciseIds());
   }

   @PostMapping("/{id}")
   @PreAuthorize("hasAuthority('MEMBERSHIP_WRITE')")
   @ResponseStatus(value = HttpStatus.ACCEPTED)
   public Response<Long> complete(@Valid @PathVariable @Positive Long id,
                                          Principal principal) {
      var command = new CompletePlanCommand(id, getAuthenticatedUserId(principal));
      completePlanUC.complete(command);
      return respond(id);
   }

   private Long getAuthenticatedUserId(Principal principal) {
      return Long.valueOf(principal.getName());
   }
}