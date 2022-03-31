package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.repository.CityRepository;
import com.sport.support.branch.adapter.out.persistence.repository.DistrictRepository;
import com.sport.support.branch.application.port.out.CheckCityValidityPort;
import com.sport.support.branch.application.port.out.CheckDistrictValidityPort;
import com.sport.support.branch.domain.LocationErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements CheckCityValidityPort, CheckDistrictValidityPort {

   private final CityRepository cityRepository;
   private final DistrictRepository districtRepository;

   @Override
   public void doesCityExistById(Long id) {
      if (!cityRepository.existsById(id))
         throw new BusinessRuleException(LocationErrorMessages.ERROR_CITY_IS_NOT_FOUND);
   }

   @Override
   public void doesDistrictExistById(Long id) {
      if (!districtRepository.existsById(id))
         throw new BusinessRuleException(LocationErrorMessages.ERROR_DISTRICT_IS_NOT_FOUND);
   }
}
