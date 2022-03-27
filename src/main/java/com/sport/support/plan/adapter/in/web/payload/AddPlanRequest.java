package com.sport.support.plan.adapter.in.web.payload;

import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import java.util.Set;

@Data
public class AddPlanRequest {

   @NotBlank
   private String username;

   @Valid
   private Set<AddDayPlanRequest> dayPlans;

}
