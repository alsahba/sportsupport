package com.sport.support.plan.application.port.out;

import java.util.Set;

public interface RemovePlanExercisePort {
   void removeExercise(Set<Long> ids);
}
