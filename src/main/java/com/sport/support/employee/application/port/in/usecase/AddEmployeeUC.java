package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.domain.Employee;

public interface AddEmployeeUC {
   Employee add(AddEmployeeCommand addEmployeeCommand);
}
