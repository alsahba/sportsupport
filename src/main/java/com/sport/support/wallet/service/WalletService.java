package com.sport.support.wallet.service;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.infrastructure.common.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.wallet.dao.WalletDAO;
import com.sport.support.wallet.entity.Wallet;
import com.sport.support.wallet.messages.WalletErrorMessages;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class WalletService {

   private final WalletDAO walletDAO;

   public WalletService(WalletDAO walletDAO) {
      this.walletDAO = walletDAO;
   }

   public void create(Wallet wallet) {
      walletDAO.save(wallet);
   }

   public void withdraw(AppUser user, Money cost) {
      Wallet wallet = user.getWallet();
      Money updatedBalance = walletDAO.updateBalance(wallet, cost.negate());
      if (updatedBalance.isGreaterThanOrEqual(Money.zero())) {
         //todo must be cached in redis like balance
         wallet.setTotalSpent(wallet.getTotalSpent().add(cost));
         walletDAO.save(wallet, cost);
      } else {
         throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
      }
   }

   @Transactional
   public void transfer(Long userId, Money money) {
      walletDAO.transfer(userId, money);
   }
}
