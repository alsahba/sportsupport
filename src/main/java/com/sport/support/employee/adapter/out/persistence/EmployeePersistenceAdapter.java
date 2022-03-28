package com.sport.support.employee.adapter.out.persistence;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import com.sport.support.employee.adapter.out.persistence.repository.EmployeeRepository;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.employee.domain.EmployeeErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements SaveEmployeePort, LoadEmployeePort {

   private final EmployeeRepository employeeRepository;

   @Override
   public void save(Employee employee) {
      employeeRepository.save(employee);
   }

   @Override
   public Employee loadByUserIdAndType(Long userId, EmployeeType type) {
      return employeeRepository.findByUserIdAndType(userId, type)
          .orElseThrow(() -> new EntityNotFoundException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND));
   }
}
