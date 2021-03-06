package com.sport.support.employee.adapter.out.persistence;

import com.sport.support.employee.adapter.out.persistence.entity.EmployeeEntity;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.employee.adapter.out.persistence.repository.EmployeeRepository;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.enumeration.EmployeeErrorMessages;
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
   public Employee update(Employee employee) {
      var entity = findById(employee.getId());
      var updatedEntity = new EmployeeEntity(employee);
      updatedEntity.copyFrom(entity);
      return employeeRepository.save(updatedEntity).toDomain();
   }

   @Override
   public Employee loadByUserIdAndType(Long userId, EmployeeType type) {
      return employeeRepository.findByUserIdAndType(userId, type)
          .orElseThrow(() -> new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND)).toDomain();
   }

   @Override
   public Employee loadById(Long id) {
      return findById(id).toDomain();
   }

   @Override
   public boolean existsByUserIdAndType(Long userId, EmployeeType type) {
      return employeeRepository.existsByUserIdAndType(userId, type);
   }

   @Override
   public boolean existsById(Long id) {
      return employeeRepository.existsById(id);
   }

   private EmployeeEntity findById(Long id) {
      return employeeRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND));
   }
}
