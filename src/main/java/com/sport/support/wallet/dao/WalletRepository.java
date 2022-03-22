package com.sport.support.wallet.dao;

import com.sport.support.wallet.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<Wallet, Long> {

   Optional<Wallet> findByUserId(Long userId);

}