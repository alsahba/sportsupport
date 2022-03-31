package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.usecase.DeleteBranchUC;
import com.sport.support.branch.application.port.out.DeleteBranchPort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class DeleteBranchService implements DeleteBranchUC {

   private final DeleteBranchPort deleteBranchPort;

   @Override
   public void delete(Long id) {
      deleteBranchPort.delete(id);
   }

}
