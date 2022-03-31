package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.PlanExerciseEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PlanExerciseRepository extends JpaRepository<PlanExerciseEntity, Long> {

   @Modifying
   @Query("delete from PlanExerciseEntity pe where pe.id in ?1")
   void deleteByIdIn(Collection<Long> ids);

   Collection<PlanExerciseEntity> findByIdIn(Collection<Long> ids);

}
