package com.sport.support.plan.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddPlanExerciseRequest {

   @NotNull
   private Long id;

   @Min(1)
   private int sets;

}
