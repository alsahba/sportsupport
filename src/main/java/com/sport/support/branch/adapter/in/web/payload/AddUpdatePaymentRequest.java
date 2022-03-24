package com.sport.support.branch.adapter.in.web.payload;

import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
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
