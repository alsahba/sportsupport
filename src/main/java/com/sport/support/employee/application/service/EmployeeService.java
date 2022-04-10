package com.sport.support.employee.application.service;

import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.branch.application.port.in.usecase.BranchExistenceUC;
import com.sport.support.branch.domain.enumeration.BranchErrorMessages;
import com.sport.support.employee.application.port.in.command.AddEmployeeCommand;
import com.sport.support.employee.application.port.in.command.ChangeSalaryCommand;
import com.sport.support.employee.application.port.in.usecase.AddEmployeeUC;
import com.sport.support.employee.application.port.in.usecase.ChangeSalaryUC;
import com.sport.support.employee.application.port.in.usecase.CheckEmployeeValidityUC;
import com.sport.support.employee.application.port.in.usecase.LoadEmployeeUC;
import com.sport.support.employee.application.port.out.LoadEmployeePort;
import com.sport.support.employee.application.port.out.SaveEmployeePort;
import com.sport.support.employee.domain.Employee;
import com.sport.support.employee.domain.enumeration.EmployeeErrorMessages;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class EmployeeService implements AddEmployeeUC, CheckEmployeeValidityUC, LoadEmployeeUC, ChangeSalaryUC {

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

   @Override
   public void checkEmployeeExistenceById(Long id) {
      if (!loadEmployeePort.existsById(id)) {
         throw new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_IS_NOT_FOUND);
      }
   }

   @Override
   public Employee loadByUserIdAndType(Long userId, EmployeeType type) {
      return loadEmployeePort.loadByUserIdAndType(userId, type);
   }

   @Override
   public void change(ChangeSalaryCommand command) {
      var employee = loadEmployeePort.loadById(command.getEmployeeId().getId());
      var manager = loadEmployeePort.loadByUserIdAndType(command.getManagerId().getId(), EmployeeType.MANAGER);

      if (employee.getBranchId().equals(manager.getBranchId())) {
         employee.changeSalary(command.getBaseSalary(), command.getBonus());
         saveEmployeePort.update(employee);
      } else {
         throw new BusinessRuleException(EmployeeErrorMessages.ERROR_EMPLOYEE_MANAGER_IS_UNAUTHORIZED);
      }
   }

   private void checkBranch(Long branchId) {
      if (!branchExistenceUC.existsById(branchId)) {
         throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND);
      }
   }
}
