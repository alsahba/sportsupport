package com.sport.support.employee.application.port.out;

import com.sport.support.employee.domain.Employee;

public interface SaveEmployeePort {

   Employee save(Employee employee);

   Employee update(Employee employee);
}
