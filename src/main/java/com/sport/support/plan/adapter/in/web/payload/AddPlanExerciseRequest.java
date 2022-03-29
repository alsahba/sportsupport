package com.sport.support.plan.adapter.in.web.payload;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

@Data
public class AddPlanExerciseRequest {

   @NotNull
   private Long id;

   @Positive
   private int sets;

}
