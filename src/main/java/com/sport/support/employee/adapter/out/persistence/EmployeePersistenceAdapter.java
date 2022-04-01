package com.sport.support.employee.adapter.out.persistence;

import com.sport.support.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.adapter.out.persistence.repository.EmployeeRepository;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.EmployeeErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;

@PersistenceAdapter
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements SaveEmployeePort, LoadEmployeePort {

   private final EmployeeRepository employeeRepository;

   @Override
   public Employee save(Employee employee) {
      return employeeRepository.save(new EmployeeEntity(employee)).toDomain();
   }

   @Override
   public EmployeeEntity loadByUserIdAndType(Long userId, EmployeeType type) {
      return employeeRepository.findByUserIdAndType(userId, type)
          .orElseThrow(() -> new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND));
   }

   @Override
   public boolean existsByUserIdAndType(Long userId, EmployeeType type) {
      return employeeRepository.existsByUserIdAndType(userId, type);
   }
}
