package com.sport.support.wallet.adapter.out.persistence.repository;

import com.sport.support.wallet.adapter.out.persistence.entity.WalletActivityEntity;
import com.sport.support.wallet.domain.WalletActivityType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface WalletActivityRepository extends JpaRepository<WalletActivityEntity, Long> {

   List<WalletActivityEntity> findByWalletUserIdAndTypeInAndTransactionDateBetween(Long userId, Collection<WalletActivityType> type, LocalDateTime from, LocalDateTime to);

}
