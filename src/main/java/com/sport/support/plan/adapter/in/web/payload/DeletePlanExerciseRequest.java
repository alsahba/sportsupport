package com.sport.support.plan.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotEmpty;
import java.util.Set;

@Data
public class DeletePlanExerciseRequest {

   @NotEmpty
   private Set<Long> planExerciseIds;

}
