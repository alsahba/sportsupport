package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.entity.City;
import com.sport.support.branch.adapter.out.persistence.entity.District;
import com.sport.support.branch.adapter.out.persistence.repository.CityRepository;
import com.sport.support.branch.adapter.out.persistence.repository.DistrictRepository;
import com.sport.support.branch.application.port.out.LoadCityPort;
import com.sport.support.branch.application.port.out.LoadDistrictPort;
import com.sport.support.branch.domain.LocationErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements LoadCityPort, LoadDistrictPort {

   private final CityRepository cityRepository;
   private final DistrictRepository districtRepository;
   
   @Override
   public City loadCity(Long id) {
      return cityRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(LocationErrorMessages.ERROR_CITY_IS_NOT_FOUND));
   }

   @Override
   public District loadDistrict(Long id) {
      return districtRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(LocationErrorMessages.ERROR_DISTRICT_IS_NOT_FOUND));
   }
}
