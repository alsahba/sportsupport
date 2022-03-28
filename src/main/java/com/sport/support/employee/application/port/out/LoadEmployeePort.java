package com.sport.support.employee.application.port.out;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;

public interface LoadEmployeePort {
   Employee loadByUserIdAndType(Long userId, EmployeeType type);
}
