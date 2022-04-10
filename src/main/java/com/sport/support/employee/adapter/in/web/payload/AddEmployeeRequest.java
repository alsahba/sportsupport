package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.shared.common.money.MoneyDTO;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Data
public class AddEmployeeRequest {

   @NotNull
   private Long branchId;

   @NotBlank
   private String username;

   @NotNull
   private EmployeeType type;

   private MoneyDTO baseSalary;

   private MoneyDTO bonus;

   @NotNull
   private LocalDateTime startDate;

}
