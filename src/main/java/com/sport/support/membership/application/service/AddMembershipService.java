package com.sport.support.membership.application.service;

import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.domain.Branch;
import com.sport.support.employee.application.port.in.usecase.CheckEmployeeValidityUC;
import com.sport.support.employee.domain.enumeration.EmployeeType;
import com.sport.support.infrastructure.common.annotations.stereotype.UseCase;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.security.enumeration.RoleEnum;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import com.sport.support.membership.application.port.out.PublishWithdrawMoneyPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.Membership;
import com.sport.support.membership.domain.MembershipErrorMessages;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.Type;
import lombok.RequiredArgsConstructor;

@UseCase
@RequiredArgsConstructor
public class AddMembershipService implements AddMembershipUC {

   private final UpdateRoleUC updateRoleUC;
   private final LoadUserUC loadUserUC;
   private final DecreaseQuotaUC decreaseQuotaUC;
   private final CheckEmployeeValidityUC checkEmployeeValidityUC;
   private final FindBranchUC findBranchUC;
   private final PublishWithdrawMoneyPort publishWithdrawMoneyPort;
   private final DoesMembershipExistPort doesMembershipExistPort;
   private final SaveMembershipPort saveMembershipPort;

   // TODO: 29.03.2022 activate the passive membership
   // TODO: 29.03.2022 renewal of the membership

   @Override
   public Membership add(AddMembershipCommand command) {
      var membership = command.toDomain();

      checkUserIsAlreadyMember(membership.getUserId());
      checkEmployeeValidityUC.checkEmployeeExistenceByUserIdAndType(membership.getTrainerId(), EmployeeType.TRAINER);

      var branch = findBranchUC.findById(new FindBranchQuery(membership.getBranchId()));
      var user = loadUserUC.loadById(membership.getUserId());

      decreaseQuotaUC.decreaseQuota(new BranchMembershipCommand(membership.getBranchId()));
      updateRoleUC.update(new UpdateRoleCommand(user, RoleEnum.MEMBER));
      receivePayment(user.getId(), branch, membership.getType(), membership.getDuration());

      return saveMembershipPort.save(membership);
   }

   private void receivePayment(Long userId, Branch branch, Type type, Duration duration) {
      Money cost = branch.getCost(type, duration);
      publishWithdrawMoneyPort.publishWithdrawMoney(userId, cost);
   }

   private void checkUserIsAlreadyMember(Long userId) {
      if (doesMembershipExistPort.doesExistByUser(userId)) {
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER);
      }
   }
}
