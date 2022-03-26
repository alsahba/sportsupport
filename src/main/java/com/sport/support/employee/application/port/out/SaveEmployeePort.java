package com.sport.support.employee.application.port.out;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;

public interface SaveEmployeePort {
   void save(Employee employee);
}
