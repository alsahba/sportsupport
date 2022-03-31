package com.sport.support.wallet.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.wallet.adapter.out.persistence.entity.WalletEntity;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import com.sport.support.wallet.domain.Wallet;
import com.sport.support.wallet.domain.WalletErrorMessages;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

@PersistenceAdapter
@RequiredArgsConstructor
class WalletPersistenceAdapter implements UpdateWalletBalancePort, CreateWalletPort, LoadWalletPort {

   private final WalletRepository walletRepository;
   private final RedissonClient redissonClient;

   private final String LOCK_PREFIX = "wallet-lock-";

   @Override
   public Wallet create(Long userId) {
      var entity = createNewEntity(userId);
      return walletRepository.save(entity).toDomain();
   }

   @Override
   public void update(Wallet wallet) {
      acquireLock(wallet.getId());
      updateBalance(wallet);
      releaseLock(wallet.getId());
   }

   @Override
   public Wallet load(Long userId) {
      return walletRepository.findByUserId(userId)
          .orElseThrow(() -> new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND))
          .toDomain();
   }

   private void acquireLock(Long id) {
      RLock lock = redissonClient.getLock(LOCK_PREFIX + id);
      lock.lock();
   }

   private void releaseLock(Long id) {
      RLock lock = redissonClient.getLock(LOCK_PREFIX + id);
      lock.unlock();
   }

   private WalletEntity createNewEntity(Long userId) {
      var entity = new WalletEntity();
      entity.setBalance(Money.zero());
      entity.setTotalSpent(Money.zero());
      entity.setUser(new AppUser(userId));
      return entity;
   }

   private void updateBalance(Wallet wallet) {
      WalletEntity entity = findById(wallet.getId());
      entity.setBalance(wallet.getBalance());
      entity.setTotalSpent(wallet.getTotalSpent());
      walletRepository.save(entity);
   }

   private WalletEntity findById(Long id) {
      return walletRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND));
   }
}
