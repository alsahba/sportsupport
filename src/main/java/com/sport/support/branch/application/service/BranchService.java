package com.sport.support.branch.application.service;

import com.sport.support.branch.adapter.out.persistence.BranchPersistenceAdapter;
import com.sport.support.branch.adapter.out.persistence.PaymentRepository;
import com.sport.support.branch.adapter.out.persistence.Branch;
import com.sport.support.membership.adapter.out.persistence.Membership;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

// TODO: 5/16/2021 branch stats
// TODO: 5/19/2021 if a branch is closed, members should be transfered to other branches
@Service
@RequiredArgsConstructor
public class BranchService {

   private final PaymentRepository paymentRepository;
   private final BranchPersistenceAdapter branchPersistenceAdapter;

   @Transactional
   public void add(Branch branch) {
      branchPersistenceAdapter.save(branch);
      paymentRepository.saveAll(branch.getPayments());
   }

   public Page<Branch> findAll(PageRequest pageRequest) {
      return branchPersistenceAdapter.findAll(pageRequest);
   }

   public Branch findById(Long id) {
      return branchPersistenceAdapter.findById(id);
   }

   @Transactional
   public void update(Branch branch) {
      Branch branchDb = findById(branch.getId());
      branchDb.update(branch);
      branchPersistenceAdapter.save(branchDb);
   }

   public void delete(Long id) {
      branchPersistenceAdapter.delete(id);
   }

   @Transactional
   public void checkout(Membership membership) {
      Branch branch = findById(membership.getBranch().getId());
      membership.setBranch(branch);
      branchPersistenceAdapter.updateQuota(branch, -1);
   }

   @Transactional
   public void releaseQuota(Branch branch) {
      branchPersistenceAdapter.updateQuota(branch, 1);
   }
}
