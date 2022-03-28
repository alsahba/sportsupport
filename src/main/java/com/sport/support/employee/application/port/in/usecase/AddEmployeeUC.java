package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;

public interface AddEmployeeUC {
   void add(AddEmployeeCommand addEmployeeCommand);
}
