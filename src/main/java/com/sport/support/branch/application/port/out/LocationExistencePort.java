package com.sport.support.branch.application.port.out;

public interface LocationExistencePort {

   boolean doesCityExistById(Long id);

   boolean doesDistrictExistById(Long id);

}
