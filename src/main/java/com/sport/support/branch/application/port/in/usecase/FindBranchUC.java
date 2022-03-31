package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import com.sport.support.branch.domain.Branch;
import org.springframework.data.domain.Page;

public interface FindBranchUC {

   Page<Branch> findAll(FindBranchQuery query);

   Branch findById(FindBranchQuery query);

}
