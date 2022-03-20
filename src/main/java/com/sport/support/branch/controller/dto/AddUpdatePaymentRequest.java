package com.sport.support.branch.controller.dto;

import com.sport.support.membership.entity.enumeration.Duration;
import com.sport.support.membership.entity.enumeration.Type;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Data
public class AddUpdatePaymentRequest {

   @NotNull
   private Type type;

   @NotNull
   private Duration duration;

   @NotNull
   @Min(1)
   private BigDecimal cost;

}
