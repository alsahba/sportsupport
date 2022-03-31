package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.usecase.AddBranchUC;
import com.sport.support.branch.application.port.out.SaveBranchPort;
import com.sport.support.branch.application.port.out.CheckCityValidityPort;
import com.sport.support.branch.application.port.out.CheckDistrictValidityPort;
import com.sport.support.branch.domain.Branch;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddBranchService implements AddBranchUC {

   private final SaveBranchPort saveBranchPort;
   private final CheckCityValidityPort checkCityValidityPort;
   private final CheckDistrictValidityPort checkDistrictValidityPort;

   @Override
   public Branch add(AddBranchCommand command) {
      var branch = command.toDomain();
      checkCityValidityPort.doesCityExistById(branch.getCityId());
      checkDistrictValidityPort.doesDistrictExistById(branch.getDistrictId());
      return saveBranchPort.save(branch);
   }
}
