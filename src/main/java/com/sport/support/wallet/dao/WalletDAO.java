package com.sport.support.wallet.dao;

import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.exception.DatabaseException;
import com.sport.support.infrastructure.exception.RecordIsNotFoundException;
import com.sport.support.wallet.entity.Wallet;
import com.sport.support.wallet.messages.WalletErrorMessages;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.util.Optional;

@Component
public class WalletDAO {

   private final WalletRepository walletRepository;

   @Qualifier(value = "walletBalanceCache")
   private final RedisTemplate<Long, Money> redisTemplate;

   private final Duration duration = Duration.ofSeconds(60);

   public WalletDAO(WalletRepository walletRepository, RedisTemplate<Long, Money> redisTemplate) {
      this.walletRepository = walletRepository;
      this.redisTemplate = redisTemplate;
   }

   public Wallet retrieveByUserId(Long userId) {
      return walletRepository.findByUserId(userId)
          .orElseThrow(() -> new RecordIsNotFoundException(WalletErrorMessages.ERROR_WALLET_NOT_FOUND));
   }

   public void save(Wallet wallet) {
      save(wallet, wallet.getBalance());
   }

   public void save(Wallet wallet, Money change) {
      try {
         redisTemplate.opsForValue().set(wallet.getUser().getId(), wallet.getBalance(), duration);
         walletRepository.save(wallet);
      } catch (Exception e) {
         redisTemplate.opsForValue().set(wallet.getUser().getId(), wallet.getBalance().subtract(change), duration);
         throw new DatabaseException(e);
      }
   }

   public Money updateBalance(Wallet wallet, Money change) {
      Money balance = Optional.ofNullable(redisTemplate.opsForValue().get(wallet.getUser().getId()))
          .orElseGet(wallet::getBalance);
      wallet.setBalance(balance.add(change));
      return balance;
   }

   public void transfer(Long userId, Money change) {
      Wallet wallet = retrieveByUserId(userId);
      updateBalance(wallet, change);
      save(wallet, change);
   }
}
