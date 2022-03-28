package com.sport.support.appuser.application.port.in.usecase;

import com.sport.support.appuser.application.port.in.command.AddUserToBranchCommand;

public interface AddUserToBranchUC {
   void addUserToBranch(AddUserToBranchCommand command);
}
