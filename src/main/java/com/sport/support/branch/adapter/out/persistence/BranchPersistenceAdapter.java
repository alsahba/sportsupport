package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.adapter.out.persistence.repository.BranchRepository;
import com.sport.support.branch.adapter.out.persistence.repository.PaymentRepository;
import com.sport.support.branch.application.port.out.*;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
public class BranchPersistenceAdapter
    implements SaveBranchPort, LoadBranchPort, UpdateQuotaPort, DeleteBranchPort, UpdateBranchPort {

   private final BranchRepository branchRepository;
   private final PaymentRepository paymentRepository;
   private final RedissonClient redissonClient;

   private final String LOCK_PREFIX = "branch-quota-lock-";

   @Override
   public void updateQuota(Branch branch, int change) {
      acquireLock(branch.getId());

      branch.setQuota(branch.getQuota() + change);
      branchRepository.save(branch);

      releaseLock(branch.getId());
   }

   @Override
   public void save(Branch branch) {
      branchRepository.save(branch);
      paymentRepository.saveAll(branch.getPayments());
   }

   @Override
   public void update(Branch branch) {
      acquireLock(branch.getId());
      save(branch);
      releaseLock(branch.getId());
   }

   @Override
   public Page<Branch> loadAll(PageRequest pageRequest) {
      return branchRepository.findAll(pageRequest);
   }

   @Override
   public Branch loadById(Long id) {
      return branchRepository.findById(id)
          .orElseThrow(() -> new EntityNotFoundException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND));
   }

   @Override
   public void delete(Branch branch) {
      branchRepository.delete(branch);
   }

   private void acquireLock(Long id) {
      RLock lock = redissonClient.getLock(LOCK_PREFIX + id);
      lock.lock();
   }

   private void releaseLock(Long id) {
      RLock lock = redissonClient.getLock(LOCK_PREFIX + id);
      lock.unlock();
   }
}
