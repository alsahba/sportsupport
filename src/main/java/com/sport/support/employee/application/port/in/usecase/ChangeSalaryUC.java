package com.sport.support.employee.application.port.in.usecase;

import com.sport.support.employee.application.port.in.command.ChangeSalaryCommand;

public interface ChangeSalaryUC {

   void change(ChangeSalaryCommand command);

}
