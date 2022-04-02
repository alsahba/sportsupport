package com.sport.support.wallet.adapter.out.persistence.repository;

import com.sport.support.wallet.adapter.out.persistence.entity.WalletEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WalletRepository extends JpaRepository<WalletEntity, Long> {

   Optional<WalletEntity> findByUserId(Long userId);

}
