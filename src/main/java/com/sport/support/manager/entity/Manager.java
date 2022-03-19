package com.sport.support.manager.entity;

import com.sport.support.appuser.entity.AppUser;
import com.sport.support.branch.entity.Branch;
import com.sport.support.infrastructure.abstractions.entity.AbstractEmployee;
import com.sport.support.manager.controller.dto.AddManagerRequest;
import com.sport.support.manager.controller.dto.UpdateManagerRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
public class Manager extends AbstractEmployee {

    public Manager(AddManagerRequest addManagerRequest) {
        setAppUser(new AppUser(addManagerRequest.getUserId()));
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
