package com.sport.support.branch.adapter.out.persistence.repository;

import com.sport.support.branch.adapter.out.persistence.entity.CityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<CityEntity, Long> {

   boolean existsById(Long id);

}
