package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
public class AddEmployeeRequest {

   @NotBlank
   private String username;

   @NotNull
   private EmployeeType type;

   private BigDecimal baseSalary;

   private BigDecimal bonus;

   @NotNull
   private LocalDateTime startDate;

}
