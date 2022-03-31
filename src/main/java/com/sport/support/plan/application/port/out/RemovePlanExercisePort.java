package com.sport.support.plan.application.port.out;

import com.sport.support.plan.domain.PlanExercise;

import java.util.Set;

public interface RemovePlanExercisePort {
   void removeExercise(Set<PlanExercise> planExercises);
}
