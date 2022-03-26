package com.sport.support.branch.application.port.out;

import com.sport.support.branch.adapter.out.persistence.entity.City;

public interface LoadCityPort {
   City loadCity(Long id);
}
