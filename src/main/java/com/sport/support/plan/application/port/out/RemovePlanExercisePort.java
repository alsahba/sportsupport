package com.sport.support.plan.application.port.out;

import com.sport.support.plan.adapter.out.persistence.entity.PlanExercise;

import java.util.Set;

public interface RemovePlanExercisePort {
   void removeExercise(Set<PlanExercise> planExercises);
}
