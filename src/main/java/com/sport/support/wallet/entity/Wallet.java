package com.sport.support.wallet.entity;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.infrastructure.abstractions.entity.AbstractAuditableEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
public class Wallet extends AbstractAuditableEntity {

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "USER_ID", referencedColumnName = "ID")
    private AppUser user;

    private Double balance;

    private Double totalSpent;

}
