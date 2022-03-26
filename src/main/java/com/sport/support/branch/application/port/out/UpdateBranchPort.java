package com.sport.support.branch.application.port.out;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;

public interface UpdateBranchPort {
   void update(Branch branch);
}
