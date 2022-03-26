package com.sport.support.branch.application.port.in.command;

import lombok.Getter;

@Getter
public class BranchMembershipCommand {
   private final Long branchId;

   public BranchMembershipCommand(Long branchId) {
      this.branchId = branchId;
   }
}
