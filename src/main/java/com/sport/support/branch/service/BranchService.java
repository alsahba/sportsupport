package com.sport.support.branch.service;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.branch.repository.BranchRepository;
import com.sport.support.infrastructure.abstractions.entity.AbstractEntity;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import com.sport.support.infrastructure.specifications.SpecificationFactory;
import com.sport.support.infrastructure.specifications.SpecificationName;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

// TODO: 5/16/2021 branch stats
// TODO: 5/19/2021 if a branch is closed, members should be transfered to other branches
@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final SpecificationFactory<AbstractEntity> specificationFactory;

    public Long add(Branch branch) {
        return branchRepository.save(branch).getId();
    }

    public List<Branch> retrieveAll() {
        return branchRepository.findAll();
    }

    public Branch retrieveById(Long id) {
        return branchRepository.findById(id)
                .orElseThrow(() -> new RecordDoesNotExistException(BranchErrorMessages.BRANCH_DOES_NOT_EXIST));
    }

    public void update(Branch branch) {
        Branch branchDb = branchRepository.findById(branch.getId()).get();
        if (specificationFactory.execute(SpecificationName.BRANCH_EXISTS, branchDb)) {
            branchDb.update(branch);
            branchRepository.save(branchDb);
        }
    }

    public void delete(Long id) {
        Branch branchDb = branchRepository.findById(id).get();
        if (specificationFactory.execute(SpecificationName.BRANCH_EXISTS, branchDb)) {
            branchRepository.delete(branchDb);
        }
    }
}
