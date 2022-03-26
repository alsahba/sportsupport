package com.sport.support.branch.application.port.in.command;

import lombok.Getter;
import org.springframework.data.domain.PageRequest;

@Getter
public class FindBranchQuery {
   private final PageRequest pageRequest;
   private final Long branchId;

   public FindBranchQuery(PageRequest pageRequest) {
      this.pageRequest = pageRequest;
      this.branchId = null;
   }

   public FindBranchQuery(Long branchId) {
      this.branchId = branchId;
      this.pageRequest = null;
   }
}
