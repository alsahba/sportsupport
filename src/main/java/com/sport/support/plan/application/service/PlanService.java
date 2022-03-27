package com.sport.support.plan.application.service;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.application.port.in.usecase.AddPlanUC;
import com.sport.support.plan.application.port.out.SavePlanPort;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PlanService implements AddPlanUC {

   private final LoadUserUC loadUserUC;
   private final SavePlanPort savePlanPort;

   @Override
   public void add(AddPlanCommand addPlanCommand) {
      var user = loadUserUC.loadByUsername(addPlanCommand.getUsername());
      var plans = addPlanCommand.getDayPlans().stream()
          .map(dayPlan -> new Plan(dayPlan, user)).collect(Collectors.toSet());
      savePlanPort.save(plans);
   }
}
