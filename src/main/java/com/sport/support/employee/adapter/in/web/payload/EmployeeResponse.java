package com.sport.support.employee.adapter.in.web.payload;

import com.sport.support.employee.domain.Employee;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class EmployeeResponse {

   private Long id;
   private Long userId;
   private Long branchId;
   private String type;

   public static EmployeeResponse success(Employee employee) {
      return EmployeeResponse.builder()
          .id(employee.getId())
          .userId(employee.getUserId())
          .branchId(employee.getBranchId())
          .type(employee.getType().name())
          .build();
   }
}
