package com.sport.support.branch.application.port.out;

import com.sport.support.branch.domain.Branch;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface LoadBranchPort {
   Page<Branch> loadAll(PageRequest pageRequest);

   Branch loadById(Long id);
}
