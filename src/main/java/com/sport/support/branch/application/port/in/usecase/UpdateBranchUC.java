package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;

public interface UpdateBranchUC {
   void update(UpdateBranchCommand command);
}
