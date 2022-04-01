package com.sport.support.wallet.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.domain.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Table(name = "WALLET")
@Getter
@Setter
@NoArgsConstructor
public class WalletEntity extends AbstractAuditableEntity {

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private AppUserEntity user;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride( name = "amount", column = @Column(name = "BALANCE_AMOUNT")),
    })
    private Money balance;

    @Embedded
    @AttributeOverrides(value = {
        @AttributeOverride( name = "amount", column = @Column(name = "TOTAL_SPENT_AMOUNT")),
    })
    private Money totalSpent;

    public Wallet toDomain() {
        return Wallet.builder()
            .id(getId())
            .userId(getUser().getId())
            .balance(getBalance())
            .totalSpent(getTotalSpent())
            .build();
    }
}
