package com.sport.support.branch.application.service;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.application.port.in.usecase.UpdateBranchUC;
import com.sport.support.branch.application.port.out.LoadBranchPort;
import com.sport.support.branch.application.port.out.UpdateBranchPort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateBranchService implements UpdateBranchUC {

   private final LoadBranchPort loadBranchPort;
   private final UpdateBranchPort updateBranchPort;

   @Override
   public void update(UpdateBranchCommand command) {
      Branch branch = loadBranchPort.loadById(command.getId());
      branch.update(command);
      updateBranchPort.update(branch);
   }
}
