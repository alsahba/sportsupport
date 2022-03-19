package com.sport.support.branch.controller.dto;

import com.sport.support.membership.entity.enumeration.Type;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class AddUpdatePaymentRequest {

   @NotNull
   private Type type;

   @NotNull
   @Min(1)
   private Double oneTimeAmount;

   @NotNull
   @Min(1)
   private Double monthlyAmount;

   @NotNull
   @Min(1)
   private Double annualAmount;

}
