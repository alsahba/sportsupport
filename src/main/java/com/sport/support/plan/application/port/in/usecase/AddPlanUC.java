package com.sport.support.plan.application.port.in.usecase;

import com.sport.support.plan.application.port.in.command.AddPlanCommand;

public interface AddPlanUC {
   void add(AddPlanCommand command);
}
