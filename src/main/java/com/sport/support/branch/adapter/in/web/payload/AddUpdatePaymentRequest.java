package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.math.BigDecimal;

@Data
public class AddUpdatePaymentRequest {

   @NotNull
   private Type type;

   @NotNull
   private Duration duration;

   @NotNull
   @Positive
   private BigDecimal cost;

}
