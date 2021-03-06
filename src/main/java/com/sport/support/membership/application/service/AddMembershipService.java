package com.sport.support.membership.application.service;

import com.sport.support.appuser.application.port.in.command.UpdateRoleCommand;
import com.sport.support.appuser.application.port.in.usecase.LoadUserUC;
import com.sport.support.appuser.application.port.in.usecase.UpdateRoleUC;
import com.sport.support.appuser.domain.vo.UserId;
import com.sport.support.branch.application.port.in.command.BranchMembershipCommand;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.application.port.in.usecase.DecreaseQuotaUC;
import com.sport.support.branch.application.port.in.usecase.FindBranchUC;
import com.sport.support.branch.domain.Branch;
import com.sport.support.employee.application.port.in.usecase.CheckEmployeeValidityUC;
import com.sport.support.membership.application.port.in.command.AddMembershipCommand;
import com.sport.support.membership.application.port.in.usecase.AddMembershipUC;
import com.sport.support.membership.application.port.out.DoesMembershipExistPort;
import com.sport.support.membership.application.port.out.PublishWithdrawMoneyPort;
import com.sport.support.membership.application.port.out.SaveMembershipPort;
import com.sport.support.membership.domain.Membership;
import com.sport.support.membership.domain.enumeration.Duration;
import com.sport.support.membership.domain.enumeration.MembershipErrorMessages;
import com.sport.support.membership.domain.enumeration.Type;
import com.sport.support.shared.common.annotations.stereotype.UseCase;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.shared.security.enumeration.RoleEnum;
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
      checkEmployeeValidityUC.checkEmployeeExistenceById(membership.getTrainerId().getId());

      var branch = findBranchUC.findById(new FindBranchQuery(membership.getBranchId().getId()));
      var user = loadUserUC.loadById(membership.getUserId().getId());

      decreaseQuotaUC.decreaseQuota(new BranchMembershipCommand(membership.getBranchId()));
      updateRoleUC.update(new UpdateRoleCommand(user.getId(), RoleEnum.MEMBER));
      receivePayment(user.getId(), branch, membership.getType(), membership.getDuration());

      return saveMembershipPort.save(membership);
   }

   private void receivePayment(Long userId, Branch branch, Type type, Duration duration) {
      Money cost = branch.getCost(type, duration);
      publishWithdrawMoneyPort.publishWithdrawMoney(userId, cost);
   }

   private void checkUserIsAlreadyMember(UserId userId) {
      if (doesMembershipExistPort.doesExistByUser(userId.getId())) {
         throw new BusinessRuleException(MembershipErrorMessages.ERROR_MEMBERSHIP_USER_IS_ALREADY_MEMBER);
      }
   }
}
