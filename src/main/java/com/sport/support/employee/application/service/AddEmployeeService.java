package com.sport.support.employee.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.application.port.in.command.AddUserToBranchCommand;
import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.AddUserToBranchUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.appuser.application.port.out.LoadUserPort;
import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddEmployeeService implements AddEmployeeUC {

   private final LoadUserPort loadUserPort;
   private final SaveEmployeePort saveEmployeePort;
   private final UpdateRoleUC updateRoleUC;
   private final AddUserToBranchUC addUserToBranchUC;

   public void add(AddEmployeeCommand command) {
      AppUser appUser = loadUserPort.loadByUsername(command.getUsername());
      Employee employee = new Employee(command, appUser);
      employee.setUser(appUser);
      addUserToBranchUC.addUserToBranch(new AddUserToBranchCommand(appUser, command.getBranchId()));
      updateRoleUC.update(new UpdateRoleCommand(appUser, employee.getType().getRole()));
      saveEmployeePort.save(employee);
   }
}
