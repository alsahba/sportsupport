package com.sport.support.branch.service;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.branch.repository.BranchRepository;
import com.sport.support.branch.repository.PaymentRepository;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import com.sport.support.membership.entity.Membership;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.List;
import java.util.Optional;

// TODO: 5/16/2021 branch stats
// TODO: 5/19/2021 if a branch is closed, members should be transfered to other branches
@Service
public class BranchService {

   private final BranchRepository branchRepository;
   private final PaymentRepository paymentRepository;

   @Qualifier(value = "branchQuotaCache")
   private final RedisTemplate<Long, Integer> redisTemplate;

   private final Duration duration = Duration.ofSeconds(60);

   public BranchService(BranchRepository branchRepository, PaymentRepository paymentRepository, RedisTemplate<Long, Integer> redisTemplate) {
      this.branchRepository = branchRepository;
      this.paymentRepository = paymentRepository;
      this.redisTemplate = redisTemplate;
   }

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

   public void checkout(Membership membership) {
      Branch branch = retrieveById(membership.getBranch().getId());
      membership.setBranch(branch);
      captureQuota(branch);
   }

   private void captureQuota(Branch branch) {
      int quota = Optional.ofNullable(redisTemplate.opsForValue().get(branch.getId()))
          .orElseGet(branch::getQuota);

      if (quota == 0) throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_QUOTA_IS_EMPTY);

      redisTemplate.opsForValue().set(branch.getId(), quota - 1, duration);
      branch.setQuota(quota - 1);
      branchRepository.save(branch);
   }

   public void releaseQuota(Branch branch) {
      int quota = Optional.ofNullable(redisTemplate.opsForValue().get(branch.getId()))
          .orElseGet(branch::getQuota);

      redisTemplate.opsForValue().set(branch.getId(), quota + 1, duration);
      branch.setQuota(quota + 1);
      branchRepository.save(branch);
   }
}
