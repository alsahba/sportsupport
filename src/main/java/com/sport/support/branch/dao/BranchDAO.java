package com.sport.support.branch.dao;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.messages.BranchErrorMessages;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.infrastructure.exception.DatabaseException;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class BranchDAO {

   private final BranchRepository branchRepository;

   @Qualifier(value = "branchQuotaCache")
   private final RedisTemplate<Long, Integer> redisTemplate;

   private final Duration duration = Duration.ofSeconds(60);

   public BranchDAO(BranchRepository branchRepository, RedisTemplate<Long, Integer> redisTemplate) {
      this.branchRepository = branchRepository;
      this.redisTemplate = redisTemplate;
   }

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

   public void save(Branch branch) {
      branchRepository.save(branch);
   }

   public Page<Branch> findAll(PageRequest pageRequest) {
      return branchRepository.findAll(pageRequest);
   }

   public Branch findById(Long id) {
      return branchRepository.findById(id)
          .orElseThrow(() -> new RecordIsNotFoundException(BranchErrorMessages.ERROR_BRANCH_IS_NOT_FOUND));
   }

   public void delete(Long id) {
      branchRepository.delete(findById(id));
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