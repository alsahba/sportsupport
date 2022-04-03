package com.sport.support.employee.domain;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.shared.abstractions.domain.AbstractDomainObject;
import com.sport.support.shared.common.money.Money;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
public class Employee extends AbstractDomainObject<EmployeeId> {

   private UserId userId;
   private Long branchId;
   private EmployeeType type;
   private Money baseSalary;
   private Money bonus;
   private LocalDate startDate;

   @Builder
   public Employee(EmployeeId idVO, UserId userId, Long branchId, EmployeeType type, Money baseSalary, Money bonus, LocalDate startDate) {
      super(idVO);
      this.userId = userId;
      this.branchId = branchId;
      this.type = type;
      this.baseSalary = baseSalary;
      this.bonus = bonus;
      this.startDate = startDate;
   }

   public Employee(Long userId, AddEmployeeCommand addEmployeeCommand) {
      this.userId = new UserId(userId);
      this.branchId = addEmployeeCommand.getBranchId();
      this.type = addEmployeeCommand.getType();
      this.baseSalary = addEmployeeCommand.getBaseSalary();
      this.bonus = addEmployeeCommand.getBonus();
      this.startDate = addEmployeeCommand.getStartDate().toLocalDate();
   }
}
