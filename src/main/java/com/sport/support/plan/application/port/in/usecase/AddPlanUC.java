package com.sport.support.plan.application.port.in.usecase;

import com.sport.support.plan.application.port.in.command.AddPlanCommand;
import com.sport.support.plan.domain.Plan;

import java.util.List;

public interface AddPlanUC {
   List<Plan> add(AddPlanCommand command);
}
