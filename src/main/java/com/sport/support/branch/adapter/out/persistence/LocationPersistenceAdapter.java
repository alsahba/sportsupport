package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.repository.CityRepository;
import com.sport.support.branch.adapter.out.persistence.repository.DistrictRepository;
import com.sport.support.branch.application.port.out.LocationExistencePort;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class LocationPersistenceAdapter implements LocationExistencePort {

   private final CityRepository cityRepository;
   private final DistrictRepository districtRepository;

   @Override
   public boolean doesCityExistById(Long id) {
      return cityRepository.existsById(id);
   }

   @Override
   public boolean doesDistrictExistById(Long id) {
      return districtRepository.existsById(id);
   }
}
