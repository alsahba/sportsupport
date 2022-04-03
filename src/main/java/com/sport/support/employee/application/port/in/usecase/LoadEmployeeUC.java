package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.enumeration.EmployeeType;

public interface LoadEmployeeUC {

   Employee loadByUserIdAndType(Long userId, EmployeeType type);

}
