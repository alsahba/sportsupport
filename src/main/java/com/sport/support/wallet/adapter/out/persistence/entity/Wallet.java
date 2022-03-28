package com.sport.support.wallet.adapter.out.persistence.entity;

import com.sport.support.appuser.adapter.out.persistence.entity.AppUser;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import com.sport.support.infrastructure.common.money.Money;
import com.sport.support.infrastructure.exception.BusinessRuleException;
import com.sport.support.wallet.domain.WalletErrorMessages;
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

    public Wallet(Long userId) {
        setBalance(Money.zero());
        setTotalSpent(Money.zero());
        setUser(new AppUser(userId));
    }

    public void withdraw(Money change) {
        setBalance(getBalance().subtract(change));
        setTotalSpent(getTotalSpent().add(change));
        if (getBalance().isNegative()) {
            throw new BusinessRuleException(WalletErrorMessages.ERROR_WALLET_INSUFFICIENT_BALANCE);
        }
    }

    public void deposit(Money change) {
        setBalance(getBalance().add(change));
    }
}
