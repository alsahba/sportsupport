package com.sport.support.employee.domain;

import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.shared.common.money.Money;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Data
@AllArgsConstructor
public class Employee {

   private Long id;
   private Long userId;
   private Long branchId;
   private EmployeeType type;
   private Money baseSalary;
   private Money bonus;
   private LocalDate startDate;

   public Employee(Long userId, AddEmployeeCommand addEmployeeCommand) {
      this.userId = userId;
      this.branchId = addEmployeeCommand.getBranchId();
      this.type = addEmployeeCommand.getType();
      this.baseSalary = addEmployeeCommand.getBaseSalary();
      this.bonus = addEmployeeCommand.getBonus();
      this.startDate = addEmployeeCommand.getStartDate().toLocalDate();
   }
}
