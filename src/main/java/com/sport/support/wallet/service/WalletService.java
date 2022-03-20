package com.sport.support.wallet.service;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.wallet.entity.Wallet;
import com.sport.support.wallet.repository.WalletRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.concurrent.TimeUnit;

@Service
@RequiredArgsConstructor
public class WalletService {

   private final WalletRepository walletRepository;
   private final RedisTemplate<Long, Wallet> redisTemplate;

   public void create(Wallet wallet) {
      walletRepository.save(wallet);
   }

   public void withdraw(AppUser user, Money cost) {
      Wallet wallet = getWallet(user);
      if (wallet.getBalance().isGreaterThanOrEqual(cost)) {
         wallet.setBalance(wallet.getBalance().subtract(cost));
         wallet.setTotalSpent(wallet.getTotalSpent().add(cost));
         redisTemplate.opsForValue().set(user.getId(), wallet);
         walletRepository.save(wallet);
      } else {
         // TODO: 20.03.2022 error message will be changed
         throw new IllegalArgumentException("Not enough money");
      }
   }

   public Wallet getWallet(AppUser user) {
      return Optional.ofNullable(redisTemplate.opsForValue().get(user.getId()))
          .orElseGet(() -> {
                 var wallet = user.getWallet();
                 redisTemplate.opsForValue().set(user.getId(), wallet, 60, TimeUnit.SECONDS);
                 return wallet;
              }
          );
   }
}
