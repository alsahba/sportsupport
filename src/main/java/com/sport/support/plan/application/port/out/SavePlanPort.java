package com.sport.support.plan.application.port.out;

import com.sport.support.plan.adapter.out.persistence.entity.Plan;
import com.sport.support.plan.adapter.out.persistence.entity.PlanExercise;

import java.util.Set;

public interface SavePlanPort {

   void save(Set<Plan> plans);

   void savePlanExercises(Set<PlanExercise> exercises);

}
