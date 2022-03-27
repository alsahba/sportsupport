package com.sport.support.plan.application.port.in.command;

import com.sport.support.plan.adapter.in.web.payload.AddPlanRequest;
import lombok.Getter;

import java.util.Set;
import java.util.stream.Collectors;

@Getter
public class AddPlanCommand {

   private final String username;
   private final Set<DailyPlanCommand> dayPlans;

   public AddPlanCommand(AddPlanRequest request) {
      this.username = request.getUsername();
      this.dayPlans = request.getDayPlans().stream().map(DailyPlanCommand::new).collect(Collectors.toSet());
   }
}
