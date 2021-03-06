package com.sport.support.wallet.adapter.out.persistence.entity;

import com.sport.support.shared.abstractions.entity.AbstractEntity;
import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.domain.WalletActivity;
import com.sport.support.wallet.domain.enumeration.WalletActivityType;
import com.sport.support.wallet.domain.vo.WalletActivityId;
import com.sport.support.wallet.domain.vo.WalletId;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "WALLET_ACTIVITY")
@NoArgsConstructor
public class WalletActivityEntity extends AbstractEntity {

   @ManyToOne
   private WalletEntity wallet;

   @Embedded
   @AttributeOverrides(value = {
       @AttributeOverride( name = "amount", column = @Column(name = "change_amount")),
   })
   private Money amount;

   private WalletActivityType type;

   private LocalDateTime transactionDate;

   public WalletActivity toDomain() {
      return WalletActivity.builder()
          .idVO(new WalletActivityId(getId()))
          .walletId(new WalletId(getWallet().getId()))
          .type(getType())
          .amount(getAmount())
          .transactionDate(getTransactionDate())
          .build();
   }

   public WalletActivityEntity(WalletActivity walletActivity) {
      setWallet(new WalletEntity(walletActivity.getWalletId().getId()));
      setAmount(walletActivity.getAmount());
      setTransactionDate(walletActivity.getTransactionDate());
      setType(walletActivity.getType());
   }

}
