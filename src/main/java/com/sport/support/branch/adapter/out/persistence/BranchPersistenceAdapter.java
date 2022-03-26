package com.sport.support.branch.adapter.out.persistence;

import com.sport.support.branch.adapter.out.persistence.entity.Branch;
import com.sport.support.branch.adapter.out.persistence.repository.BranchRepository;
import com.sport.support.branch.adapter.out.persistence.repository.PaymentRepository;
import com.sport.support.branch.application.port.out.DeleteBranchPort;
import com.sport.support.branch.application.port.out.LoadBranchPort;
import com.sport.support.branch.application.port.out.SaveBranchPort;
import com.sport.support.branch.application.port.out.UpdateQuotaPort;
import com.sport.support.branch.domain.BranchErrorMessages;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.exception.DatabaseException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.util.Optional;

@PersistenceAdapter
public class BranchPersistenceAdapter implements SaveBranchPort, LoadBranchPort, UpdateQuotaPort, DeleteBranchPort {

   private final BranchRepository branchRepository;
   private final PaymentRepository paymentRepository;

   @Qualifier(value = "branchQuotaCache")
   private final RedisTemplate<Long, Integer> redisTemplate;

   private final Duration duration = Duration.ofSeconds(60);

   public BranchPersistenceAdapter(BranchRepository branchRepository, PaymentRepository paymentRepository, RedisTemplate<Long, Integer> redisTemplate) {
      this.branchRepository = branchRepository;
      this.paymentRepository = paymentRepository;
      this.redisTemplate = redisTemplate;
   }

   @Override
   public void updateQuota(Branch branch, int change) {
      int quota = Optional.ofNullable(redisTemplate.opsForValue().get(branch.getId()))
          .orElseGet(branch::getQuota);
      var newQuota = quota + change;

      if (newQuota < 0) {
         throw new BusinessRuleException(BranchErrorMessages.ERROR_BRANCH_QUOTA_IS_EMPTY);
      }
      branch.setQuota(newQuota);
      save(branch, change);
   }

   @Override
   public void save(Branch branch) {
      branchRepository.save(branch);
      paymentRepository.saveAll(branch.getPayments());
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

   private void save(Branch branch, int quota) {
      try {
         redisTemplate.opsForValue().set(branch.getId(), branch.getQuota(), duration);
         branchRepository.save(branch);
      } catch (Exception e) {
         redisTemplate.opsForValue().set(branch.getId(), branch.getQuota() + quota, duration);
         throw new DatabaseException(e);
      }
   }
}
