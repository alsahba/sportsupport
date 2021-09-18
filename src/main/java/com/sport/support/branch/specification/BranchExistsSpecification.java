package com.sport.support.branch.specification;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.branch.repository.BranchRepository;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.AbstractSpecification;
import com.sport.support.infrastructure.specifications.SpecificationName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@RequiredArgsConstructor
public class BranchExistsSpecification extends AbstractSpecification<Branch> {

    @Override
    public boolean isSatisfiedBy(Branch branch) {
        if (branch == null) {
            throw new RecordDoesNotExistException(BranchErrorMessages.BRANCH_DOES_NOT_EXIST);
        }
        return true;
    }

    @Override
    public SpecificationName getName() {
        return SpecificationName.BRANCH_EXISTS;
    }
}
