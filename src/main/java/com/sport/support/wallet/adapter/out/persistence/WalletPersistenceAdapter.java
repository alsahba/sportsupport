package com.sport.support.wallet.adapter.out.persistence;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.shared.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.shared.common.money.Money;
import com.sport.support.shared.exception.BusinessRuleException;
import com.sport.support.wallet.adapter.out.persistence.entity.WalletActivityEntity;
import com.sport.support.wallet.adapter.out.persistence.entity.WalletEntity;
import com.sport.support.wallet.adapter.out.persistence.repository.WalletActivityRepository;
import com.sport.support.wallet.adapter.out.persistence.repository.WalletRepository;
import com.sport.support.wallet.application.port.in.command.FindWalletActivityQuery;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.UpdateWalletBalancePort;
import com.sport.support.wallet.domain.Wallet;
import com.sport.support.wallet.domain.WalletActivity;
import com.sport.support.wallet.domain.WalletErrorMessages;
import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Value;

import java.util.List;

@PersistenceAdapter
class WalletPersistenceAdapter implements UpdateWalletBalancePort, CreateWalletPort, LoadWalletPort {

   private final WalletRepository walletRepository;
   private final WalletActivityRepository walletActivityRepository;
   private final RedissonClient redissonClient;
   private final String LOCK_PREFIX;

   public WalletPersistenceAdapter(WalletRepository walletRepository,
                                   WalletActivityRepository walletActivityRepository,
                                   RedissonClient redissonClient,
                                   @Value("${lock-prefix-wallet}") String prefix) {
      this.walletRepository = walletRepository;
      this.walletActivityRepository = walletActivityRepository;
      this.redissonClient = redissonClient;
      this.LOCK_PREFIX = prefix;
   }

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
   public Wallet loadByUserId(Long userId) {
      return walletRepository.findByUserId(userId)
          .orElseThrow(() -> new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND))
          .toDomain();
   }

   @Override
   public List<WalletActivity> loadWalletActivities(FindWalletActivityQuery query) {
      List<WalletActivityEntity> entities = walletActivityRepository
          .findByWalletUserIdAndTypeInAndTransactionDateBetween(query.getUserId(),
              query.getTypes(), query.getDateRange().from(), query.getDateRange().to());
      return entities.stream().map(WalletActivityEntity::toDomain).toList();
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
      entity.setUser(new AppUserEntity(userId));
      return entity;
   }

   private void updateBalance(Wallet wallet) {
      WalletEntity entity = findById(wallet.getId());
      entity.setBalance(wallet.getBalance());
      entity.setTotalSpent(wallet.getTotalSpent());
      wallet.getActivities().forEach(this::saveActivity);
      walletRepository.save(entity);
   }

   private WalletEntity findById(Long id) {
      return walletRepository.findById(id)
          .orElseThrow(() -> new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND));
   }

   private void saveActivity(WalletActivity walletActivity) {
      walletActivityRepository.save(new WalletActivityEntity(walletActivity));
   }
}
