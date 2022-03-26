package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.application.port.in.command.AddBranchCommand;

public interface AddBranchUC {
   void add(AddBranchCommand command);
}
