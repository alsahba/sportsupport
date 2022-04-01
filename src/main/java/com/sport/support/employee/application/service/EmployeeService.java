package com.sport.support.employee.application.service;

import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.branch.application.port.in.usecase.BranchExistenceUC;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import com.sport.support.employee.application.port.in.usecase.CheckEmployeeValidityUC;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.EmployeeErrorMessages;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService implements AddEmployeeUC, CheckEmployeeValidityUC {

   private final SaveEmployeePort saveEmployeePort;
   private final LoadEmployeePort loadEmployeePort;
   private final LoadUserUC loadUserUC;
   private final UpdateRoleUC updateRoleUC;
   private final BranchExistenceUC branchExistenceUC;

   public Employee add(AddEmployeeCommand command) {
      checkBranch(command.getBranchId());

      var user = loadUserUC.loadByUsername(command.getUsername());
      var employee = new Employee(user.getId(), command);

      updateRoleUC.update(new UpdateRoleCommand(user.getId(), employee.getType().getRole()));
      return saveEmployeePort.save(employee);
   }

   @Override
   public void checkEmployeeExistenceByUserIdAndType(Long userId, EmployeeType type) {
      if (!loadEmployeePort.existsByUserIdAndType(userId, type)) {
         throw new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND);
      }
   }

   private void checkBranch(Long branchId) {
      if (!branchExistenceUC.existsById(branchId)) {
         throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND);
      }
   }
}
