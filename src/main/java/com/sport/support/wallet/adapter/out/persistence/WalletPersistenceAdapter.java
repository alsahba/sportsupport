package com.sport.support.wallet.adapter.out.persistence;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.common.annotations.stereotype.PersistenceAdapter;
import com.sport.support.infrastructure.exception.DatabaseException;
import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;
import com.sport.support.wallet.application.port.out.CreateWalletPort;
import com.sport.support.wallet.application.port.out.DepositMoneyPort;
import com.sport.support.wallet.application.port.out.LoadWalletPort;
import com.sport.support.wallet.application.port.out.WithdrawMoneyPort;
import com.sport.support.wallet.domain.WalletErrorMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;

import javax.persistence.EntityNotFoundException;
import java.time.Duration;
import java.util.Optional;

@PersistenceAdapter
class WalletPersistenceAdapter implements DepositMoneyPort, WithdrawMoneyPort, CreateWalletPort, LoadWalletPort {

   private final WalletRepository walletRepository;

   @Qualifier(value = "walletBalanceCache")
   private final RedisTemplate<Long, Money> redisTemplate;

   private final Duration duration = Duration.ofSeconds(60);

   public WalletPersistenceAdapter(WalletRepository walletRepository, RedisTemplate<Long, Money> redisTemplate) {
      this.walletRepository = walletRepository;
      this.redisTemplate = redisTemplate;
   }

   @Override
   public void deposit(Wallet wallet, Money money) {
      save(wallet, money);
   }

   @Override
   public void create(Wallet wallet) {
      save(wallet);
   }

   @Override
   public void withdraw(Wallet wallet, Money money) {
      save(wallet, money.negate());
   }

   @Override
   public Wallet load(Long userId) {
      Wallet wallet = findByUserID(userId);
      wallet.setBalance(getCurrentBalance(wallet));
      return wallet;
   }

   private Wallet findByUserID(Long userId) {
      return walletRepository.findByUserId(userId)
          .orElseThrow(() -> new EntityNotFoundException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND));
   }

   private void save(Wallet wallet) {
      save(wallet, Money.zero());
   }

   private void save(Wallet wallet, Money change) {
      try {
         redisTemplate.opsForValue().set(wallet.getUser().getId(), wallet.getBalance(), duration);
         walletRepository.save(wallet);
      } catch (Exception e) {
         redisTemplate.opsForValue().set(wallet.getUser().getId(), wallet.getBalance().subtract(change), duration);
         throw new DatabaseException(e);
      }
   }

   private Money getCurrentBalance(Wallet wallet) {
      return Optional.ofNullable(redisTemplate.opsForValue().get(wallet.getUser().getId()))
          .orElseGet(wallet::getBalance);
   }
}
