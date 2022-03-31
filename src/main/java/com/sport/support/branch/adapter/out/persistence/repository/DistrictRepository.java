package com.sport.support.branch.adapter.out.persistence.repository;

import com.sport.support.branch.adapter.out.persistence.entity.DistrictEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DistrictRepository extends JpaRepository<DistrictEntity, Long> {

   boolean existsById(Long id);

}
