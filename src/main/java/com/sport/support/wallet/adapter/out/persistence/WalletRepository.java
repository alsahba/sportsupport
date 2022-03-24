package com.sport.support.wallet.adapter.out.persistence;

import com.sport.support.wallet.adapter.out.persistence.entity.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

interface WalletRepository extends JpaRepository<Wallet, Long> {

   Optional<Wallet> findByUserId(Long userId);

}
