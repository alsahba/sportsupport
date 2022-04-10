package com.sport.support.employee.application.port.in.command;

import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.employee.adapter.in.web.payload.ChangeSalaryRequest;
import com.sport.support.employee.domain.vo.EmployeeId;
import com.sport.support.shared.common.money.Money;
import lombok.Getter;

@Getter
public class ChangeSalaryCommand {

   private final EmployeeId employeeId;
   private final UserId managerId;
   private final Money baseSalary;
   private final Money bonus;

   public ChangeSalaryCommand(Long employeeId, Long managerId, ChangeSalaryRequest request) {
      this.employeeId = new EmployeeId(employeeId);
      this.managerId = new UserId(managerId);
      this.baseSalary = new Money(request.getBaseSalary());
      this.bonus = new Money(request.getBonus());
   }
}
