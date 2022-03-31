package com.sport.support.employee.application.port.out;

import com.sport.support.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sport.support.employee.domain.enumeration.EmployeeType;

public interface LoadEmployeePort {

   EmployeeEntity loadByUserIdAndType(Long userId, EmployeeType type);

   boolean existsByUserIdAndType(Long userId, EmployeeType type);

}
