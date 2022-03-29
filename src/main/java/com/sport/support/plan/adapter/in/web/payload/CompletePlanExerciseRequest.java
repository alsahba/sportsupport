package com.sport.support.plan.adapter.in.web.payload;

import lombok.Data;

import java.util.Set;

@Data
public class CompletePlanExerciseRequest {
   private Set<Long> planExerciseIds;
}
