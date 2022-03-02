package com.sport.support.infrastructure.abstractions.entity;

import com.sport.support.branch.entity.Branch;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@AllArgsConstructor
@NoArgsConstructor
@MappedSuperclass
public abstract class AbstractEmployee extends AbstractSystemUser {

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    public abstract BigDecimal calculateSalary();
}
