package com.sport.support.wallet.entity;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.Money;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;


@Entity
@Getter
@Setter
@NoArgsConstructor
public class Wallet extends AbstractAuditableEntity {

    @OneToOne
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID", nullable = false)
    private AppUser user;

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

    public Wallet(AppUser user) {
        setBalance(Money.zero());
        setTotalSpent(Money.zero());
        setUser(user);
    }
}
