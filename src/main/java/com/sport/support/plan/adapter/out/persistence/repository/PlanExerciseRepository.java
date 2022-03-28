package com.sport.support.plan.adapter.out.persistence.repository;

import com.sport.support.plan.adapter.out.persistence.entity.PlanExercise;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.Collection;

public interface PlanExerciseRepository extends JpaRepository<PlanExercise, Long> {

   @Modifying
   @Query("delete from PlanExercise e where e.id in ?1")
   void deleteByIdIn(Collection<Long> id);

}
