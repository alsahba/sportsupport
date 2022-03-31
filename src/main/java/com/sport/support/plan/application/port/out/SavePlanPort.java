package com.sport.support.plan.application.port.out;

import com.sport.support.plan.domain.Plan;

import java.util.List;
import java.util.Set;

public interface SavePlanPort {

   List<Plan> save(List<Plan> plans);

   void updatePlanExercises(Set<Long> planExerciseIds, boolean completed);

}
