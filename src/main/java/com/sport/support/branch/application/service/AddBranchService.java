package com.sport.support.branch.application.service;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.usecase.AddBranchUC;
import com.sport.support.branch.application.port.out.SaveBranchPort;
import com.sport.support.branch.application.port.out.LoadCityPort;
import com.sport.support.branch.application.port.out.LoadDistrictPort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddBranchService implements AddBranchUC {

   private final SaveBranchPort saveBranchPort;
   private final LoadCityPort loadCityPort;
   private final LoadDistrictPort loadDistrictPort;

   @Override
   public void add(AddBranchCommand command) {
      Branch branch = new Branch(command);
      branch.setCity(loadCityPort.loadCity(command.getCityId()));
      branch.setDistrict(loadDistrictPort.loadDistrict(command.getDistrictId()));
      saveBranchPort.save(branch);
   }
}
