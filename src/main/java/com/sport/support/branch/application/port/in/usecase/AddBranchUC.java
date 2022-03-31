package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.domain.Branch;

public interface AddBranchUC {
   Branch add(AddBranchCommand command);
}
