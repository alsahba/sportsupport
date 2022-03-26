package com.sport.support.employee.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.application.port.in.command.UpdatePermissionCommand;
import com.sport.support.appuser.application.port.in.usecase.UpdatePermissionUC;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.application.port.in.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.AddEmployeeUC;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;

@UseCase
@RequiredArgsConstructor
public class AddEmployeeService implements AddEmployeeUC {

   private final LoadUserPort loadUserPort;
   private final SaveEmployeePort saveEmployeePort;
   private final UpdatePermissionUC updatePermissionUC;

   @Transactional
   public void add(AddEmployeeCommand command) {
      AppUser appUser = loadUserPort.loadByUsername(command.getUsername());
      Employee employee = new Employee(command);
      employee.setUser(appUser);
      updatePermissionUC.update(new UpdatePermissionCommand(appUser, employee.getType().getRole()));
      saveEmployeePort.save(employee);
   }
}
