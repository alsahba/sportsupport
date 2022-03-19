package com.sport.support.infrastructure.abstractions.entity;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.branch.entity.Branch;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;


@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEmployee extends AbstractAuditableEntity {

    @OneToOne
    @JoinColumn(name = "APP_USER_ID")
    private AppUser appUser;

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    public abstract BigDecimal calculateSalary();
}
