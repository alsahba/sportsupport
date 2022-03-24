package com.sport.support.employee.application.service;

import com.sport.support.appuser.application.service.AppUserDetailsManager;
import com.sport.support.employee.adapter.out.persistence.Employee;
import com.sport.support.employee.adapter.out.persistence.EmployeeRepository;
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
