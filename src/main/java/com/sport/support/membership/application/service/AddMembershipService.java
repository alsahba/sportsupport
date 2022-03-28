package com.sport.support.membership.application.service;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.employee.adapter.out.persistence.entity.Employee;
import com.sport.support.employee.adapter.out.persistence.enumeration.EmployeeType;
import com.sport.support.employee.application.port.in.command.FindEmployeeQuery;
import com.sport.support.employee.application.port.in.usecase.FindEmployeeUC;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.membership.adapter.out.persistence.entity.Membership;
import com.sport.support.membership.adapter.out.persistence.enumeration.Duration;
import com.sport.support.membership.adapter.out.persistence.enumeration.Type;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.out.LoadMembershipPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.MembershipErrorMessages;
import com.sport.support.wallet.application.port.in.command.WithdrawMoneyCommand;
import com.sport.support.wallet.application.port.in.usecase.WithdrawMoneyUC;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddMembershipService implements AddMembershipUC {

   private final WithdrawMoneyUC withdrawMoneyUC;
   private final DecreaseQuotaUC decreaseQuotaUC;
   private final UpdateRoleUC updateRoleUC;
   private final LoadUserUC loadUserUC;
   private final FindBranchUC findBranchUC;
   private final FindEmployeeUC findEmployeeUC;
   private final SaveMembershipPort saveMembershipPort;
   private final LoadMembershipPort loadMembershipPort;

   @Override
   public void add(AddMembershipCommand command) {
      Membership membership = new Membership(command);
      checkUserIsAlreadyMember(membership.getUser().getId());

      Branch branch = findBranchUC.findById(new FindBranchQuery(membership.getBranch().getId()));
      membership.setBranch(branch);

      AppUser user = loadUserUC.loadById(command.getUserId());
      membership.setUser(user);

      Employee employee = findEmployeeUC.find(new FindEmployeeQuery(command.getUserId(), EmployeeType.TRAINER));
      membership.setTrainer(employee.getUser());

      decreaseQuotaUC.decreaseQuota(new BranchMembershipCommand(branch));
      updateRoleUC.update(new UpdateRoleCommand(user, RoleEnum.MEMBER));
      recievePayment(user.getId(), branch, membership.getType(), membership.getDuration());
      saveMembershipPort.save(membership);
   }

   private void recievePayment(Long userId, Branch branch, Type type, Duration duration) {
      Money cost = branch.getCost(type, duration);
      withdrawMoneyUC.withdraw(new WithdrawMoneyCommand(userId, cost));
   }

   private void checkUserIsAlreadyMember(Long userId) {
      loadMembershipPort.loadByUserId(userId).ifPresent(membership -> {
             throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER);
          }
      );
   }
}
