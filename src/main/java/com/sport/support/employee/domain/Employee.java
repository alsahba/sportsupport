package com.sport.support.employee.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.domain.vo.BranchId;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import lombok.Getter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Getter
@SuperBuilder
public class Employee extends AbstractDomainObject<EmployeeId> {

   private UserId userId;
   private BranchId branchId;
   private EmployeeType type;
   private Money baseSalary;
   private Money bonus;
   private LocalDate startDate;

   public Employee(Long userId, AddEmployeeCommand addEmployeeCommand) {
      this.userId = new UserId(userId);
      this.branchId = new BranchId(addEmployeeCommand.getBranchId());
      this.type = addEmployeeCommand.getType();
      this.baseSalary = addEmployeeCommand.getBaseSalary();
      this.bonus = addEmployeeCommand.getBonus();
      this.startDate = addEmployeeCommand.getStartDate().toLocalDate();
   }
}
