package com.sport.support.employee.adapter.out.persistence;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.adapter.out.persistence.repository.EmployeeRepository;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements SaveEmployeePort {

   private final EmployeeRepository employeeRepository;

   @Override
   public void save(Employee employee) {
      employeeRepository.save(employee);
   }
}
