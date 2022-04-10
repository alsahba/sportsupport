package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.shared.common.money.MoneyDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

@Data
public class ChangeSalaryRequest {

   @Valid
   @NotNull
   private MoneyDTO baseSalary;

   @Valid
   @NotNull
   private MoneyDTO bonus;

}
