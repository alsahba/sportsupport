package com.sport.support.branch.application.service;

import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.branch.application.port.in.command.UpdateBranchCommand;
import com.sport.support.branch.application.port.in.usecase.UpdateBranchUC;
import com.sport.support.branch.application.port.out.UpdateBranchPort;
import com.sport.support.branch.domain.Branch;
import com.sport.support.branch.domain.enumeration.BranchErrorMessages;
import com.sport.support.employee.application.port.in.usecase.LoadEmployeeUC;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.shared.security.enumeration.RoleEnum;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class UpdateBranchService implements UpdateBranchUC {

   private final LoadUserUC loadUserUC;
   private final LoadEmployeeUC loadEmployeeUC;
   private final UpdateBranchPort updateBranchPort;

   @Override
   public void update(UpdateBranchCommand command) {
      Branch branch = command.toDomain();
      checkAuthorization(command.getUserId(), branch.getId());
      updateBranchPort.update(branch);
   }

   private void checkAuthorization(Long userId, Long branchId) {
      var user = loadUserUC.loadById(userId);
      if (!RoleEnum.OWNER.name().equals(user.getRole().getName())) {
         var employee = loadEmployeeUC.loadByUserIdAndType(userId, EmployeeType.MANAGER);
         if (!employee.getBranchId().getId().equals(branchId)) {
            throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_UNAUTHORIZED_UPDATE);
         }
      }
   }
}
