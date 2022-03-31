package com.sport.support.plan.adapter.in.web.payload;

import com.sport.support.plan.domain.Plan;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;
import java.util.Set;
import java.util.stream.Collectors;

@Data
@Builder
@AllArgsConstructor
public final class PlanResponse  {

   private Long planId;
   private LocalDate date;
   private Set<PlanExerciseResponse> exercises;

   public PlanResponse(Plan plan) {
      this.planId = plan.getId();
      this.date = plan.getDate();
      this.exercises = plan.getPlanExercises().stream().map(PlanExerciseResponse::new).collect(Collectors.toSet());
   }
}
