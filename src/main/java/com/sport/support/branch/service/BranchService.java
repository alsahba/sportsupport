package com.sport.support.branch.service;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.branch.respository.BranchRepository;
import com.sport.support.infrastructure.exception.RecordDoesNotExistException;
import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;

// TODO: 5/16/2021 branch stats
// TODO: 5/19/2021 if a branch is closed, members should be transfered to other branches
@Service
@RequiredArgsConstructor
public class BranchService {

    private final BranchRepository branchRepository;
    private final MessageSource messageSource;

    public Long add(Branch branch) {
        return branchRepository.save(branch).getId();
    }

    public List<Branch> retrieveAll() {
        return branchRepository.findAll();
    }

    public Branch retrieveById(Long id) {
        return branchRepository.findById(id).orElse(new Branch());
    }

    public void update(Branch branch) {
        branchRepository.findById(branch.getId()).ifPresentOrElse(branchDb -> {
            branchDb.update(branch);
            branchRepository.save(branchDb);
        }, () -> {
            throw new RecordDoesNotExistException(messageSource.getMessage(
                    BranchErrorMessages.BRANCH_DOES_NOT_EXIST, null, Locale.US));
        });
    }

    public void delete(Long id) {
        branchRepository.findById(id).ifPresentOrElse(branchRepository::delete, () -> {
            throw new RecordDoesNotExistException(messageSource.getMessage(
                    BranchErrorMessages.BRANCH_DOES_NOT_EXIST, null, Locale.US));
        });
    }
}
