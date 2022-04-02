package com.sport.support.wallet.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUserEntity;
import com.sport.support.shared.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.shared.common.money.Money;
import com.sport.support.wallet.domain.Wallet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;


@Entity
@Table(name = "WALLET")
@Getter
@Setter
@NoArgsConstructor
public class WalletEntity extends AbstractAuditableEntity {

    @OneToOne
    @JoinColumn(nullable = false)
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

    @OneToMany(mappedBy = "wallet")
    private Set<WalletActivityEntity> activities = new HashSet<>();

    public WalletEntity(Long id) {
        super(id);
    }

    public Wallet toDomain() {
        return Wallet.builder()
            .id(getId())
            .userId(getUser().getId())
            .balance(getBalance())
            .totalSpent(getTotalSpent())
            .activities(activities.stream().map(WalletActivityEntity::toDomain).collect(Collectors.toSet()))
            .build();
    }
}
