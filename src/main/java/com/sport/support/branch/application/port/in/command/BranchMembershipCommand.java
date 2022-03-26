package com.sport.support.branch.application.port.in.command;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import lombok.Getter;

@Getter
public class BranchMembershipCommand {
   private final Branch branch;

   public BranchMembershipCommand(Branch branch) {
      this.branch = branch;
   }
}
