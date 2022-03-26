package com.sport.support.employee.application.port.in;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import com.sport.support.infrastructure.common.Money;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddEmployeeCommand {

   private final String username;
   private final EmployeeType type;
   private final Money baseSalary;
   private final Money bonus;
   private final LocalDateTime startDate;

   public AddEmployeeCommand(AddEmployeeRequest request) {
      this.username = request.getUsername();
      this.type = request.getType();
      this.baseSalary = new Money(request.getBaseSalary());
      this.bonus = new Money(request.getBonus());
      this.startDate = request.getStartDate();
   }
}
