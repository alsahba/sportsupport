package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.employee.domain.Employee;
import lombok.Getter;

@Getter
public class EmployeeResponse {

   private final Long id;
   private final Long userId;
   private final Long branchId;
   private final String type;

   public EmployeeResponse (Employee employee) {
      this.id = employee.getId();
      this.userId = employee.getUserId().getId();
      this.branchId = employee.getBranchId().getId();
      this.type = employee.getType().toString();
   }
}
