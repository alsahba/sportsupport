package com.sport.support.wallet.service;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.wallet.entity.Wallet;
import com.sport.support.wallet.messages.WalletErrorMessages;
import com.sport.support.wallet.repository.WalletRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
public class WalletService {

   private final WalletRepository walletRepository;

   @Qualifier(value = "walletBalanceCache")
   private final RedisTemplate<Long, Money> redisTemplate;

   public WalletService(WalletRepository walletRepository, RedisTemplate<Long, Money> redisTemplate) {
      this.walletRepository = walletRepository;
      this.redisTemplate = redisTemplate;
   }

   public void create(Wallet wallet) {
      walletRepository.save(wallet);
   }

   public void withdraw(AppUser user, Money cost) {
      Wallet wallet = user.getWallet();
      Money balance = getBalance(wallet);
      if (balance.isGreaterThanOrEqual(cost)) {
         wallet.setBalance(balance.subtract(cost));
         //todo must be cached in redis like balance
         wallet.setTotalSpent(wallet.getTotalSpent().add(cost));
         redisTemplate.opsForValue().set(user.getId(), wallet.getBalance(), 60, TimeUnit.SECONDS);
         walletRepository.save(wallet);
      } else {
         throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
      }
   }

   private Money getBalance(Wallet wallet) {
      return Optional.ofNullable(redisTemplate.opsForValue().get(wallet.getUser().getId()))
          .orElseGet(wallet::getBalance);
   }
}
