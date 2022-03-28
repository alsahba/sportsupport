package com.sport.support.plan.application.port.in.usecase;

import com.sport.support.plan.application.port.in.command.DeletePlanCommand;

public interface DeletePlanUC {
   void delete(DeletePlanCommand command);
}
