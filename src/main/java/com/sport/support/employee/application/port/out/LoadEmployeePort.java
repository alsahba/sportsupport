package com.sport.support.employee.application.port.out;

import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.enumeration.EmployeeType;

public interface LoadEmployeePort {

   Employee loadByUserIdAndType(Long userId, EmployeeType type);

   boolean existsByUserIdAndType(Long userId, EmployeeType type);

   boolean existsById(Long id);

}
