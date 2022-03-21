package com.sport.support.branch.service;

import com.sport.support.branch.dao.BranchDAO;
import com.sport.support.branch.dao.PaymentRepository;
import com.sport.support.branch.entity.Branch;
import com.sport.support.membership.entity.Membership;
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
   private final BranchDAO branchDAO;

   @Transactional
   public void add(Branch branch) {
      branchDAO.save(branch);
      paymentRepository.saveAll(branch.getPayments());
   }

   public Page<Branch> findAll(PageRequest pageRequest) {
      return branchDAO.findAll(pageRequest);
   }

   public Branch findById(Long id) {
      return branchDAO.findById(id);
   }

   @Transactional
   public void update(Branch branch) {
      Branch branchDb = findById(branch.getId());
      branchDb.update(branch);
      branchDAO.save(branchDb);
   }

   public void delete(Long id) {
      branchDAO.delete(id);
   }

   @Transactional
   public void checkout(Membership membership) {
      Branch branch = findById(membership.getBranch().getId());
      membership.setBranch(branch);
      branchDAO.updateQuota(branch, -1);
   }

   @Transactional
   public void releaseQuota(Branch branch) {
      branchDAO.updateQuota(branch, 1);
   }
}
