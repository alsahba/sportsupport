package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.entity.BranchEntity;
import com.sport.support.branch.adapter.out.persistence.repository.BranchRepository;
import com.sport.support.branch.adapter.out.persistence.repository.PaymentRepository;
import com.sport.support.branch.application.port.out.*;
import com.sport.support.branch.domain.Branch;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.exception.BusinessRuleException;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

@PersistenceAdapter
@RequiredArgsConstructor
public class BranchPersistenceAdapter implements SaveBranchPort, LoadBranchPort, UpdateQuotaPort,
    DeleteBranchPort, UpdateBranchPort,BranchExistencePort {

   private final BranchRepository branchRepository;
   private final PaymentRepository paymentRepository;
   private final RedissonClient redissonClient;

   @Value("${lock-prefix.branch-quota}")
   private String LOCK_PREFIX;

   @Override
   public void updateQuota(Long branchId, int change) {
      acquireLock(branchId);

      BranchEntity branchEntity = findById(branchId);
      branchEntity.setQuota(branchEntity.getQuota() + change);
      branchRepository.save(branchEntity);

      releaseLock(branchEntity.getId());
   }

   @Override
   public Branch save(Branch branch) {
      var entity = new BranchEntity(branch);

      branchRepository.save(entity);
      paymentRepository.saveAll(entity.getPayments());

      return branch;
   }

   @Override
   public void update(Branch branch) {
      acquireLock(branch.getId());

      var updatedEntity = new BranchEntity(branch);
      var entity = findById(branch.getId());

      updatedEntity.update(entity);
      branchRepository.save(updatedEntity);

      releaseLock(branch.getId());
   }

   @Override
   public Page<Branch> loadAll(PageRequest pageRequest) {
      return branchRepository.findAll(pageRequest).map(BranchEntity::toDomain);
   }

   @Override
   public Branch loadById(Long id) {
      return findById(id).toDomain();
   }

   @Override
   public void delete(Long id) {
      branchRepository.findById(id).ifPresentOrElse(branchRepository::delete,
          () -> {
             throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND);
          });
   }

   @Override
   public boolean existsById(Long id) {
      return branchRepository.existsById(id);
   }

   private BranchEntity findById(Long id) {
      return branchRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND));
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
