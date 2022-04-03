package com.sport.support.branch.application.service;

import com.sport.support.branch.application.port.in.command.AddBranchCommand;
import com.sport.support.branch.application.port.in.usecase.AddBranchUC;
import com.sport.support.branch.application.port.out.LocationExistencePort;
import com.sport.support.branch.application.port.out.SaveBranchPort;
import com.sport.support.branch.domain.Branch;
import com.sport.support.branch.domain.enumeration.LocationErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddBranchService implements AddBranchUC {

   private final SaveBranchPort saveBranchPort;
   private final LocationExistencePort locationExistencePort;

   @Override
   public Branch add(AddBranchCommand command) {
      var branch = command.toDomain();
      checkLocation(branch.getCityId().getId(), branch.getDistrictId().getId());
      locationExistencePort.doesDistrictExistById(branch.getDistrictId().getId());
      return saveBranchPort.save(branch);
   }

   private void checkLocation(Long cityId, Long districtId) {
      if (!locationExistencePort.doesCityExistById(cityId)) {
         throw new BusinessRuleException(LocationErrorMessages.ERROR_CITY_IS_NOT_FOUND);
      }
      if (!locationExistencePort.doesDistrictExistById(districtId)) {
         throw new BusinessRuleException(LocationErrorMessages.ERROR_DISTRICT_IS_NOT_FOUND);
      }
   }
}
