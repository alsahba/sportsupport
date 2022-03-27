package com.sport.support.plan.adapter.in.web.payload;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.Set;

@Data
public class AddDayPlanRequest {

   @NotNull
   @JsonFormat(shape=JsonFormat.Shape.STRING, pattern="yyyy-MM-dd")
   private LocalDate date;

   @Valid
   private Set<AddPlanExerciseRequest> exercises;

}
