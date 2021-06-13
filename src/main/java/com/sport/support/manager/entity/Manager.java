package com.sport.support.manager.entity;

import com.sport.support.branch.entity.Branch;
import com.sport.support.branch.specification.BranchExistsSpecification;
import com.sport.support.infrastructure.abstractions.entity.AbstractEmployee;
import com.sport.support.manager.controller.dto.AddManagerDTO;
import com.sport.support.manager.controller.dto.UpdateManagerDTO;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;

@Entity
@Table(name = "MANAGER")
@Data
@NoArgsConstructor
public class Manager extends AbstractEmployee {

    @ManyToOne
    @JoinColumn(name = "BRANCH_ID", referencedColumnName = "ID", nullable = false)
    private Branch branch;

    public Manager(AddManagerDTO addManagerDTO) {
        setBranch(new Branch(addManagerDTO.getBranchId()));
        setName(addManagerDTO.getName());
        setSurname(addManagerDTO.getSurname());
        setUsername(addManagerDTO.getUsername());
        setPassword(addManagerDTO.getPassword());
        setEMail(addManagerDTO.getEMail());
        setPhoneNumber(addManagerDTO.getPhoneNumber());
    }

    public Manager(UpdateManagerDTO updateManagerDTO) {
        setId(updateManagerDTO.getId());
        setBranch(new Branch(updateManagerDTO.getBranchId()));
        setName(updateManagerDTO.getName());
        setSurname(updateManagerDTO.getSurname());
        setUsername(updateManagerDTO.getUsername());
        setPassword(updateManagerDTO.getPassword());
        setEMail(updateManagerDTO.getEMail());
        setPhoneNumber(updateManagerDTO.getPhoneNumber());
    }

    public void update(Manager manager) {
        setBranch(manager.getBranch());
        setName(manager.getName());
        setSurname(manager.getSurname());
        setUsername(manager.getUsername());
        setPassword(manager.getPassword());
        setEMail(manager.getEMail());
        setPhoneNumber(manager.getPhoneNumber());
    }

    public boolean isBranchExists() {
        return new BranchExistsSpecification().isSatisfiedBy(this.branch);
    }

    @Override
    public BigDecimal calculateSalary() {
        // TODO: 6/6/2021
        return BigDecimal.ZERO;
    }
}
