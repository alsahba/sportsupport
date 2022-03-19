package com.sport.support.branch.service;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.branch.repository.BranchRepository;
import com.sport.support.branch.repository.PaymentRepository;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

// TODO: 5/16/2021 branch stats
// TODO: 5/19/2021 if a branch is closed, members should be transfered to other branches
@Service
@RequiredArgsConstructor
public class BranchService {

   private final BranchRepository branchRepository;
   private final PaymentRepository paymentRepository;

   @Transactional
   public void add(Branch branch) {
      branchRepository.save(branch);
      paymentRepository.saveAll(branch.getPayments());
   }

   public List<Branch> retrieveAll() {
      return branchRepository.findAll();
   }

   public Branch retrieveById(Long id) {
      return branchRepository.findById(id)
          .orElseThrow(() -> new RecordIsNotFoundException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND));
   }

   public void update(Branch branch) {
      Branch branchDb = retrieveById(branch.getId());
      branchDb.update(branch);
      branchRepository.save(branchDb);
   }

   public void delete(Long id) {
      branchRepository.delete(retrieveById(id));
   }
}
