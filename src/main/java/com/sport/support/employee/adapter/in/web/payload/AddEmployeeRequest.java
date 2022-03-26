package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import com.sport.support.infrastructure.common.MoneyDTO;
import lombok.Data;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AddEmployeeRequest {

   @NotBlank
   private String username;

   @NotNull
   private EmployeeType type;

   @Valid
   private MoneyDTO baseSalary;

   @Valid
   private MoneyDTO bonus;

   @NotNull
   private LocalDateTime startDate;

}
