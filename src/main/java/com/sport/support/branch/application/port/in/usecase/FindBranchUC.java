package com.sport.support.branch.application.port.in.usecase;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.application.port.in.command.FindBranchQuery;
import org.springframework.data.domain.Page;

public interface FindBranchUC {

   Page<Branch> findAll(FindBranchQuery query);

   Branch findById(FindBranchQuery query);

}
