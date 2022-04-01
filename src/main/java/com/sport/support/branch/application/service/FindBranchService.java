package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.application.port.out.LoadBranchPort;
import com.sport.support.branch.domain.Branch;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;

@UseCase
@RequiredArgsConstructor
public class FindBranchService implements FindBranchUC {

   private final LoadBranchPort loadBranchPort;

   @Override
   public Page<Branch> findAll(FindBranchQuery query) {
      return loadBranchPort.loadAll(query.getPageRequest());
   }

   @Override
   public Branch findById(FindBranchQuery query) {
      return loadBranchPort.loadById(query.getBranchId());
   }
}
