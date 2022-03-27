package com.sport.support.plan.adapter.in.web;

import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequestMapping("/plans")
@RequiredArgsConstructor
public class PlanController {

   private final AddPlanUC addPlanUC;

   @PostMapping
   @PreAuthorize("hasAuthority('PLAN_WRITE')")
   public ResponseEntity addPlan(@Valid @RequestBody AddPlanRequest request) {
      addPlanUC.add(new AddPlanCommand(request));
      return ResponseEntity.ok().build();
   }

}