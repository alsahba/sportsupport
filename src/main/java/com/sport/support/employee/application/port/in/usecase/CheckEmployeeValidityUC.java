package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.domain.enumeration.EmployeeType;

public interface CheckEmployeeValidityUC {

   void checkEmployeeExistenceByUserIdAndType(Long userId, EmployeeType type);

   void checkEmployeeExistenceById(Long id);

}
