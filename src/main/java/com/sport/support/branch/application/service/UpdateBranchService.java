package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.application.port.in.usecase.UpdateBranchUC;
import com.sport.support.branch.application.port.out.UpdateBranchPort;
import com.sport.support.branch.domain.Branch;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateBranchService implements UpdateBranchUC {

   private final UpdateBranchPort updateBranchPort;

   @Override
   public void update(UpdateBranchCommand command) {
      Branch branch = command.toDomain();
      updateBranchPort.update(branch);
   }
}
