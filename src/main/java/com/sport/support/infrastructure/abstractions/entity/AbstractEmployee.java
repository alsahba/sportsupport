package com.sport.support.infrastructure.abstractions.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class AbstractEmployee extends AbstractSystemUser {

    @Column(name = "START_DATE")
    private LocalDateTime startDate;

    public abstract BigDecimal calculateSalary();
}
