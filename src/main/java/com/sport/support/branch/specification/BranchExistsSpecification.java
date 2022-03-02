package com.sport.support.branch.specification;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import com.sport.support.infrastructure.specifications.AbstractSpecification;
import com.sport.support.infrastructure.specifications.SpecificationName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BranchExistsSpecification extends AbstractSpecification<Branch> {

    @Override
    public boolean isSatisfiedBy(Branch branch) {
        if (branch == null) {
            throw new RecordIsNotFoundException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND);
        }
        return true;
    }

    @Override
    public SpecificationName getName() {
        return SpecificationName.BRANCH_EXISTS;
    }
}
