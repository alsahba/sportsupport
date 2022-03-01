package com.sport.support.manager.entity;

import com.sport.support.branch.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractEmployee;
import com.sport.support.manager.controller.dto.AddManagerRequest;
import com.sport.support.manager.controller.dto.UpdateManagerRequest;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "MANAGER")
@Data
@NoArgsConstructor
public class Manager extends AbstractEmployee {

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    public Manager(AddManagerRequest addManagerRequest) {
        setBranch(new Branch(addManagerRequest.getBranchId()));
    }

    public Manager(UpdateManagerRequest updateManagerRequest) {
        setId(updateManagerRequest.getId());
        setBranch(new Branch(updateManagerRequest.getBranchId()));
    }

    @Override
    public BigDecimal calculateSalary() {
        // TODO: 2.03.2022 implement
        return BigDecimal.valueOf(1000);
    }
}
