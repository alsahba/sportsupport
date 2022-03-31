package com.sport.support.plan.application.port.out;

import com.sport.support.plan.domain.Plan;
import com.sport.support.plan.domain.PlanExercise;

import java.util.List;
import java.util.Set;

public interface SavePlanPort {

   List<Plan> save(List<Plan> plans);

   void savePlanExercises(Set<PlanExercise> exercises);

   void updatePlanExercises(Set<Long> planExerciseIds, boolean completed);

}
