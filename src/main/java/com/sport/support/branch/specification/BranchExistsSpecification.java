package com.sport.support.branch.specification;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.AbstractSpecification;

public class BranchExistsSpecification extends AbstractSpecification<Branch> {

    @Override
    public boolean isSatisfiedBy(Branch branch) {
        if (branch.getId() == null) {
            throw new RecordDoesNotExistException(BranchErrorMessages.BRANCH_DOES_NOT_EXIST);
        }
        return true;
    }
}
