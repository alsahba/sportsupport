package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.PlanExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface PlanExerciseRepository extends JpaRepository<PlanExerciseEntity, Long> {

   void deleteByIdIn(Collection<Long> ids);

   Collection<PlanExerciseEntity> findByIdIn(Collection<Long> ids);

}
