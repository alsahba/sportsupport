package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.application.port.in.command.FindEmployeeQuery;

public interface FindEmployeeUC {
   Employee find(FindEmployeeQuery findEmployeeQuery);
}
