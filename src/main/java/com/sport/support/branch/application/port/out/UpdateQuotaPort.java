package com.sport.support.branch.application.port.out;

import com.sport.support.branch.domain.vo.BranchId;

public interface UpdateQuotaPort {

   void updateQuota(BranchId branchId, int change);

}
