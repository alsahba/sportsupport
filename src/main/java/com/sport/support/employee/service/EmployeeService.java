package com.sport.support.employee.service;

import com.sport.support.appuser.application.service.AppUserDetailsManager;
import com.sport.support.employee.entity.Employee;
import com.sport.support.employee.repository.EmployeeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
public class EmployeeService {

   private final AppUserDetailsManager appUserDetailsManager;
   private final EmployeeRepository employeeRepository;

   @Transactional
   public void add(Employee employee) {
      appUserDetailsManager.updatePermissions(employee);
      employeeRepository.save(employee);
   }
}
