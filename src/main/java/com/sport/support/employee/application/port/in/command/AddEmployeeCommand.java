package com.sport.support.employee.application.port.in.command;

import com.sport.support.employee.adapter.in.web.payload.AddEmployeeRequest;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.shared.common.money.Money;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class AddEmployeeCommand {

   private final Long branchId;
   private final String username;
   private final EmployeeType type;
   private final Money baseSalary;
   private final Money bonus;
   private final LocalDateTime startDate;

   public AddEmployeeCommand(AddEmployeeRequest request) {
      this.branchId = request.getBranchId();
      this.username = request.getUsername();
      this.type = request.getType();
      this.baseSalary = new Money(request.getBaseSalary().doubleValue());
      this.bonus = new Money(request.getBonus().doubleValue());
      this.startDate = request.getStartDate();
   }
}
