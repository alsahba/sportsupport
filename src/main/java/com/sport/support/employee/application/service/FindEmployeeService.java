package com.sport.support.employee.application.service;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.application.port.in.command.FindEmployeeQuery;
import com.sport.support.employee.application.port.in.usecase.FindEmployeeUC;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class FindEmployeeService implements FindEmployeeUC {

   private final LoadEmployeePort loadEmployeePort;

   @Override
   public Employee find(FindEmployeeQuery findEmployeeQuery) {
      return loadEmployeePort.loadByUserIdAndType(findEmployeeQuery.getUserId(), findEmployeeQuery.getType());
   }
}
