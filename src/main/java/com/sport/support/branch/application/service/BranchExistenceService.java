package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.usecase.BranchExistenceUC;
import com.sport.support.branch.application.port.out.BranchExistencePort;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class BranchExistenceService implements BranchExistenceUC {

   private final BranchExistencePort branchExistencePort;

   public boolean existsById(Long id) {
      return branchExistencePort.existsById(id);
   }
}
