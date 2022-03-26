package com.sport.support.wallet.adapter.out.persistence;

import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import com.sport.support.wallet.domain.WalletErrorMessages;
import lombok.RequiredArgsConstructor;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;

import javax.persistence.EntityNotFoundException;

@PersistenceAdapter
@RequiredArgsConstructor
class WalletPersistenceAdapter implements UpdateWalletBalancePort, CreateWalletPort, LoadWalletPort {

   private final WalletRepository walletRepository;
   private final RedissonClient redissonClient;

   private final String LOCK_PREFIX = "wallet-lock-";

   @Override
   public void create(Wallet wallet) {
      walletRepository.save(wallet);
   }

   @Override
   public void update(Wallet wallet) {
      acquireLock(wallet.getId());
      walletRepository.save(wallet);
      releaseLock(wallet.getId());
   }

   @Override
   public Wallet load(Long userId) {
      return walletRepository.findByUserId(userId)
          .orElseThrow(() -> new EntityNotFoundException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND));
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
